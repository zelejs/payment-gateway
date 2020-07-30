package com.jfeat.am.modular.wechat.service.impl;

import com.jfeat.am.modular.wechat.event.LocationUpdateBean;
import com.jfeat.am.modular.wechat.event.LocationUpdateEvent;
import com.jfeat.am.modular.wechat.service.LocationUpdateNotifySenderService;
import com.jfeat.am.modular.wechat.service.NotifySenderExtension;
import com.jfeat.module.event.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 如果没有指定 NotifySenderExtension bean，那么地理位置更新通知会通过这个类
 * @author jackyhuang
 * @date 2018/3/10
 */
@Service
@ConditionalOnMissingBean(NotifySenderExtension.class)
public class LocationUpdateNotifySenderServiceImpl implements LocationUpdateNotifySenderService {
    private static Logger logger = LoggerFactory.getLogger(LocationUpdateNotifySenderServiceImpl.class);

    @Resource
    EventService eventService;

    @Override
    public boolean send(String appId, String openid, BigDecimal latitude, BigDecimal longitude, BigDecimal precision) {
        logger.debug("default impl.");
        LocationUpdateBean locationUpdateBean = new LocationUpdateBean();
        locationUpdateBean.setAppId(appId);
        locationUpdateBean.setOpenid(openid);
        locationUpdateBean.setLatitude(latitude);
        locationUpdateBean.setLongitude(longitude);
        locationUpdateBean.setPrecision(precision);
        eventService.publishEvent(new LocationUpdateEvent(this, locationUpdateBean));
        return true;
    }
}
