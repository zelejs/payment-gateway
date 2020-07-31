package com.jfeat.am.modular.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.jfeat.am.modular.wechat.service.InFollowNotifySenderService;
import com.jfeat.am.modular.wechat.service.InQrCodeNotifySenderService;
import com.jfeat.am.modular.wechat.service.LocationUpdateNotifySenderService;
import com.jfinal.weixin.sdk.jfinal.MsgControllerAdapter;
import com.jfinal.weixin.sdk.msg.in.InNotDefinedMsg;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;

import java.math.BigDecimal;

/**
 * Created by jackyhuang on 2017/7/3.
 */
//@DependsOn("springContextHolder")
public class WeixinMsgController extends MsgControllerAdapter {

    private static Logger logger = LoggerFactory.getLogger(WeixinMsgController.class);

    //private LocationUpdateNotifySenderService locationUpdateNotifySenderService = SpringContextHolder.getBean(LocationUpdateNotifySenderService.class);
    //private InFollowNotifySenderService inFollowNotifySenderService = SpringContextHolder.getBean(InFollowNotifySenderService.class);
    //private InQrCodeNotifySenderService inQrCodeNotifySenderService = SpringContextHolder.getBean(InQrCodeNotifySenderService.class);

    @Override
    protected void processInFollowEvent(InFollowEvent inFollowEvent) {
        logger.debug("processInFollowEvent - {}", JSON.toJSONString(inFollowEvent));
        if (InFollowEvent.EVENT_INFOLLOW_SUBSCRIBE.equals(inFollowEvent.getEvent())) {
            //inFollowNotifySenderService.subscribe(inFollowEvent.getToUserName(), inFollowEvent.getFromUserName());
        } else {
            //inFollowNotifySenderService.unsubscribe(inFollowEvent.getToUserName(), inFollowEvent.getFromUserName());
        }
        renderNull();
    }

    @Override
    protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
        logger.debug("processInQrCodeEvent - {}", JSON.toJSONString(inQrCodeEvent));
        if (InQrCodeEvent.EVENT_INQRCODE_SUBSCRIBE.equals(inQrCodeEvent.getEvent())) {
            // 关注事件
            logger.debug("calling inQrCodeNotifySenderService.subscribe.");
            /*inQrCodeNotifySenderService.subscribe(inQrCodeEvent.getToUserName(),
                    inQrCodeEvent.getFromUserName(),
                    inQrCodeEvent.getEventKey(),
                    inQrCodeEvent.getTicket());*/
        } else if (InQrCodeEvent.EVENT_INQRCODE_SCAN.equals(inQrCodeEvent.getEvent())) {
            // 扫码事件
            logger.debug("calling inQrCodeNotifySenderService.scan.");
           /* inQrCodeNotifySenderService.scan(inQrCodeEvent.getToUserName(),
                    inQrCodeEvent.getFromUserName(),
                    inQrCodeEvent.getEventKey(),
                    inQrCodeEvent.getTicket());*/
        }
        super.processInQrCodeEvent(inQrCodeEvent);
    }

    @Override
    protected void processIsNotDefinedEvent(InNotDefinedEvent inNotDefinedEvent) {
        renderNull();
    }

    @Override
    protected void processIsNotDefinedMsg(InNotDefinedMsg inNotDefinedMsg) {
        renderNull();
    }

    @Override
    protected void processInTextMsg(InTextMsg inTextMsg) {
        renderNull();
    }

    @Override
    protected void processInMenuEvent(InMenuEvent inMenuEvent) {
        renderNull();
    }

    @Override
    protected void processInLocationEvent(InLocationEvent inLocationEvent) {
        logger.debug("openid {}, lat: {}, lng: {}", inLocationEvent.getFromUserName(), inLocationEvent.getLatitude(), inLocationEvent.getLongitude());
        /*locationUpdateNotifySenderService.send(
                inLocationEvent.getToUserName(),
                inLocationEvent.getFromUserName(),
                BigDecimal.valueOf(Double.parseDouble(inLocationEvent.getLatitude())),
                BigDecimal.valueOf(Double.parseDouble(inLocationEvent.getLongitude())),
                BigDecimal.valueOf(Double.parseDouble(inLocationEvent.getPrecision())));*/
        renderNull();
    }
}
