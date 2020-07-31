package com.jfeat.am.modular.wechat.api.pub;

import com.jfeat.am.common.persistence.model.WechatConfig;
import com.jfeat.am.modular.wechat.service.PaidNotifySenderService;
import com.jfeat.am.modular.wechat.service.WechatConfigService;
import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackyhuang on 2017/8/29.
 */
@RestController
@RequestMapping("/api/pub/wpay")
public class WechatPayNotifyEndpoint   {

    @Resource
    private PaidNotifySenderService paidNotifySenderService;
    @Resource
    private WechatConfigService wechatConfigService;

    private final Logger logger = LoggerFactory.getLogger(WechatPayNotifyEndpoint.class);

    @PostMapping("/notify/{appId}")
    public String payNotify(@PathVariable String appId) {
        String xmlMsg = HttpKit.readData(null/*getHttpServletRequest()*/);
        logger.info("pay_notify=" + xmlMsg);
        Map params = PaymentKit.xmlToMap(xmlMsg);
        String result_code = (String)params.get("result_code");
        String totalFee = (String)params.get("total_fee");
        String orderNumber = (String)params.get("out_trade_no");
        String transId = (String)params.get("transaction_id");
        String timeEnd = (String)params.get("time_end");

        WechatConfig wechatConfig = wechatConfigService.getByAppId(appId);
        if (wechatConfig == null) {
            logger.error("wechat config not found for appid {}", appId);
            throw new RuntimeException("invalid appid");
        }

        if(PaymentKit.verifyNotify(params, wechatConfig.getPartnerKey()) && "SUCCESS".equals(result_code)) {
            logger.info("Update order info: orderNumber = {}", orderNumber);
            paidNotifySenderService.send(orderNumber, "WECHAT", transId);

            HashMap xml = new HashMap();
            xml.put("return_code", "SUCCESS");
            xml.put("return_msg", "OK");
            return PaymentKit.toXml(xml);
        }

        logger.error("payment verify notify failed.");
        return "";
    }
}
