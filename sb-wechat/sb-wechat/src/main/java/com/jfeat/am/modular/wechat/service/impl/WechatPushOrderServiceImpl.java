package com.jfeat.am.modular.wechat.service.impl;

import com.google.common.collect.Maps;
import com.jfeat.am.common.exception.BizExceptionEnum;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.common.persistence.model.WechatConfig;
import com.jfeat.am.core.support.StrKit;
import com.jfeat.am.core.util.JsonKit;
import com.jfeat.am.modular.system.service.TenantService;
import com.jfeat.am.modular.wechat.constant.TradeType;
import com.jfeat.am.modular.wechat.service.WechatConfigService;
import com.jfeat.am.modular.wechat.service.WechatPushOrderService;
import com.jfinal.kit.Ret;
import com.jfinal.weixin.sdk.api.PaymentApi;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.jfeat.am.modular.wechat.constant.TradeType.WXA_TYPE;

/**
 *
 * @author Silent-Y
 * @date 2017/10/12
 */
@Service
public class WechatPushOrderServiceImpl implements WechatPushOrderService {

    private static final Logger logger = LoggerFactory.getLogger(WechatPushOrderService.class);

    @Resource
    WechatConfigService wechatConfigService;
    @Resource
    TenantService tenantService;

    @Override
    public Map<String, String> pushOrder(String title,
                         String detail,
                         String orderNum,
                         String totalFee,
                         String openid,
                         String ip,
                         String apiHost,
                         String tradeType) {

        WechatConfig wechatConfig = wechatConfigService.getByTenantId(tenantService.getDefaultTenant().getId());
        String appid = WXA_TYPE.equalsIgnoreCase(tradeType) ? wechatConfig.getWxaAppId() : wechatConfig.getAppId();
        Map params = new HashMap<>();
        params.put("appid", appid);
        params.put("mch_id", wechatConfig.getPartnerId());
        params.put("body", title);
        params.put("detail", detail);
        params.put("out_trade_no", orderNum);
        params.put("total_fee", totalFee);
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        if (StrKit.isBlank(ip)) {
            ip = "127.0.0.1";
        }
        params.put("spbill_create_ip", ip);
        params.put("trade_type", TradeType.types.get(tradeType));
        params.put("nonce_str", System.currentTimeMillis() / 1000L + "");
        params.put("notify_url", apiHost + "/api/pub/wpay/notify/" + appid);
        params.put("openid", openid);
        String sign = PaymentKit.createSign(params, wechatConfig.getPartnerKey());
        params.put("sign", sign);
        String xmlResult = PaymentApi.pushOrder(params);
        Map result = PaymentKit.xmlToMap(xmlResult);

        logger.debug("push order result: {}", result);

        String returnCode = (String) result.get("return_code");
        if (!StrKit.isBlank(returnCode) && "SUCCESS".equals(returnCode)) {
            String resultCode = (String) result.get("result_code");
            if (!StrKit.isBlank(resultCode) && "SUCCESS".equals(resultCode)) {
                String prepayId = (String) result.get("prepay_id");
                String codeUrl = (String) result.get("code_url");
                Map<String, String> packageParams = new HashMap<>();
                packageParams.put("appId", appid);
                packageParams.put("timeStamp", System.currentTimeMillis() / 1000L + "");
                packageParams.put("nonceStr", System.currentTimeMillis() + "");
                packageParams.put("package", "prepay_id=" + prepayId);
                packageParams.put("signType", "MD5");
                String packageSign = PaymentKit.createSign(packageParams, wechatConfig.getPartnerKey());
                packageParams.put("paySign", packageSign);
                packageParams.put("codeUrl", codeUrl);
                return packageParams;
            }
        }

        throw new BusinessException(BizExceptionEnum.SERVER_ERROR.getCode(), xmlResult);
    }


    @Override
    public boolean refund(String outTradeNo, String outRefundNo, BigDecimal totalFee, BigDecimal refundFee) {
        WechatConfig wechatConfig = wechatConfigService.getByTenantId(tenantService.getDefaultTenant().getId());
        HashMap<String, String> params = Maps.newHashMap();
        params.put("appid", wechatConfig.getAppId());
        params.put("mch_id", wechatConfig.getPartnerId());
        params.put("out_trade_no", outTradeNo);
        params.put("out_refund_no", outRefundNo);
        String totalFeeStr = convertPrice(totalFee.doubleValue());
        String refundFeeStr = convertPrice(refundFee.doubleValue());
        params.put("total_fee", totalFeeStr);
        params.put("refund_fee", refundFeeStr);
        params.put("op_user_id", wechatConfig.getPartnerId());
        logger.debug("refund: params = {}", params);
        Map result = PaymentApi.refund(params, wechatConfig.getPartnerKey(), wechatConfig.getCertPath());
        logger.debug("refund result = {}", result);
        String return_code = (String) result.get("return_code");
        StringBuilder message = new StringBuilder();
        message.append(result.get("return_msg"));
        if (!com.jfinal.kit.StrKit.isBlank(return_code) && "SUCCESS".equals(return_code)) {
            String result_code = (String) result.get("result_code");
            if (!com.jfinal.kit.StrKit.isBlank(result_code) && "SUCCESS".equals(result_code)) {
                logger.info("refund success: {}", result);
                return true;
            }
            message.append(result.get("err_code"));
            message.append(result.get("err_code_des"));
        }
        logger.error("refund result: {}", result);
        return false;
    }

    /**
     * 微信支付要求total_fee字段是整数，单位是分
     *
     * @param price
     * @return
     */
    private String convertPrice(double price) {
        return String.valueOf(BigDecimal.valueOf(price).multiply(BigDecimal.valueOf(100)).intValue());
    }
}
