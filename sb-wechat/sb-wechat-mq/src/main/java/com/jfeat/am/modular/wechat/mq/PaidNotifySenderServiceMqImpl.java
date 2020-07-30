package com.jfeat.am.modular.wechat.mq;

import com.google.common.collect.Maps;
import com.jfeat.am.config.web.EnabledAccountShiroExtension;
import com.jfeat.am.core.util.JsonKit;
import com.jfeat.am.modular.wechat.service.PaidNotifySenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by jackyhuang on 2017/8/29.
 */
@Component
public class PaidNotifySenderServiceMqImpl implements PaidNotifySenderService {

    private static final Logger logger = LoggerFactory.getLogger(PaidNotifySenderServiceMqImpl.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public boolean send(String orderNumber, String paymentType, String transactionId) {
        logger.debug("mq impl.");
        Map<String, Object> data = Maps.newHashMap();
        data.put("id", orderNumber);
        data.put("paymentType", paymentType);
        data.put("transactionId", transactionId);
        this.rabbitTemplate.convertAndSend(Const.PAID_NOTIFY_QUEUE, JsonKit.toJson(data));
        return true;
    }
}
