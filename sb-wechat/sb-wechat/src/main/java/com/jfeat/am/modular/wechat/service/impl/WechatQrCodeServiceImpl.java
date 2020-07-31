package com.jfeat.am.modular.wechat.service.impl;

import com.jfeat.am.common.persistence.model.WechatConfig;
import com.jfeat.am.modular.wechat.bean.QrCode;
import com.jfeat.am.modular.wechat.service.WechatConfigService;
import com.jfeat.am.modular.wechat.service.WechatQrCodeService;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfinal.kit.JsonKit;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.QrcodeApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jackyhuang
 * @date 2018/3/12
 */
@Service
public class WechatQrCodeServiceImpl implements WechatQrCodeService {

    private static Logger logger = LoggerFactory.getLogger(WechatQrCodeService.class);

    @Resource
    WechatConfigService wechatConfigService;

    @Override
    public QrCode createTemporary(Long tenantId, Integer expireSeconds, Integer sceneId) {
        WechatConfig wechatConfig = wechatConfigService.getByTenantId(tenantId);
        ApiConfigKit.setThreadLocalAppId(wechatConfig.getAppId());
        ApiResult apiResult = QrcodeApi.createTemporary(expireSeconds, sceneId);
        if (!apiResult.isSucceed()) {
            logger.error("create temporary qrcode error. {}", apiResult.toString());
            throw new BusinessException(BusinessCode.ThirdPartError);
        }
        return JsonKit.parse(apiResult.getJson(), QrCode.class);
    }

    @Override
    public QrCode createPermanent(Long tenantId, String sceneStr) {
        WechatConfig wechatConfig = wechatConfigService.getByTenantId(tenantId);
        ApiConfigKit.setThreadLocalAppId(wechatConfig.getAppId());
        ApiResult apiResult = QrcodeApi.createPermanent(sceneStr);
        if (!apiResult.isSucceed()) {
            logger.error("create permanent qrcode error. {}", apiResult.toString());
            throw new BusinessException(BusinessCode.ThirdPartError);
        }
        return JsonKit.parse(apiResult.getJson(), QrCode.class);
    }

    @Override
    public String getShowUrl(String ticket) {
        return QrcodeApi.getShowQrcodeUrl(ticket);
    }
}
