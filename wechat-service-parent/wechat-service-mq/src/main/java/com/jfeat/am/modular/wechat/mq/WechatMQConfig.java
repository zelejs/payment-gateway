package com.jfeat.am.modular.wechat.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jackyhuang on 2017/8/21.
 */
@Configuration
public class WechatMQConfig {

    @Bean
    public Queue locationUpdateNotifyQueue() {
        return new Queue(Const.LOCATION_UPDATE_NOTIFY_QUEUE);
    }

    @Bean
    public Queue paidNotifyQueue() {
        return new Queue(Const.PAID_NOTIFY_QUEUE);
    }

}
