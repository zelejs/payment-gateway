package com.jfeat.am.modular.wechat.mq;

import com.google.common.collect.Maps;
import com.jfeat.am.modular.wechat.service.LocationUpdateNotifySenderService;
import com.jfinal.kit.JsonKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 基于MQ的实现地理位置更新通知
 * @author jackyhuang
 * @date 2017/8/17
 */
@Component
public class LocationUpdateNotifySenderServiceMqImpl implements LocationUpdateNotifySenderService {

    private static final Logger logger = LoggerFactory.getLogger(LocationUpdateNotifySenderServiceMqImpl.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    //@Autowired
    //private UserService userService;

    @Override
    public boolean send(String appId, String openid, BigDecimal latitude, BigDecimal longitude, BigDecimal precision) {
        logger.debug("mq impl.");
        // user = userService.getByOpenid(openid);
        /*if (user == null) {
            logger.error("user not found. openid = {}", openid);
            return false;
        }*/
        Map<String, Object> data = Maps.newHashMap();
        //data.put("userId", user.getId());
        data.put("latitude", latitude);
        data.put("longitude", longitude);
        data.put("precision", precision);
        this.rabbitTemplate.convertAndSend(Const.LOCATION_UPDATE_NOTIFY_QUEUE, JsonKit.toJson(data));
        return true;
    }
}
