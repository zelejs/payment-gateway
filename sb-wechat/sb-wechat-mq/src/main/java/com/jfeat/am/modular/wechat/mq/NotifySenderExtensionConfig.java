package com.jfeat.am.modular.wechat.mq;

import com.jfeat.am.modular.wechat.service.NotifySenderExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jackyhuang on 2018/3/10.
 */
@Configuration
public class NotifySenderExtensionConfig {

    @Bean
    public NotifySenderExtension notifySenderExtension() {
        return new NotifySenderExtension();
    }
}
