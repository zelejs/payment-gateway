package com.jfeat.am.modular.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.jfeat.am.core.support.StrKit;
import com.jfeat.am.modular.wechat.event.InQrCodeBean;
import com.jfeat.am.modular.wechat.event.InQrCodeScanEvent;
import com.jfeat.am.modular.wechat.event.InQrCodeSubscribeEvent;
import com.jfeat.am.modular.wechat.service.InQrCodeNotifySenderService;
import com.jfeat.module.event.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author jackyhuang
 * @date 2018/3/10
 */
@Service
public class InQrCodeNotifySenderServiceImpl implements InQrCodeNotifySenderService {

    private static final Logger logger = LoggerFactory.getLogger(InQrCodeNotifySenderService.class);

    private static final String QR_CODE_SCENE_PREFIX = "qrscene_";

    @Resource
    EventService eventService;

    @Override
    public boolean subscribe(String appId, String openid, String eventKey, String ticket) {
        String sceneId = StrKit.notBlank(eventKey) ? eventKey.substring(QR_CODE_SCENE_PREFIX.length(), eventKey.length()) : null;
        InQrCodeBean inQrCodeBean = new InQrCodeBean();
        inQrCodeBean.setOpenid(openid);
        inQrCodeBean.setAppId(appId);
        inQrCodeBean.setSceneId(sceneId);
        logger.debug("publishing qrcode subscribe event: {}", JSON.toJSONString(inQrCodeBean));
        eventService.publishEvent(new InQrCodeSubscribeEvent(this, inQrCodeBean));
        return true;
    }

    @Override
    public boolean scan(String appId, String openid, String eventKey, String ticket) {
        InQrCodeBean inQrCodeBean = new InQrCodeBean();
        inQrCodeBean.setOpenid(openid);
        inQrCodeBean.setAppId(appId);
        inQrCodeBean.setSceneId(eventKey);
        logger.debug("publishing qrcode scan event: {}", JSON.toJSONString(inQrCodeBean));
        eventService.publishEvent(new InQrCodeScanEvent(this, inQrCodeBean));
        return true;
    }
}
