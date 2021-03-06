package com.jfeat.am.module.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author jackyhuang
 * @date 2018/10/17
 */
@Component
@ConfigurationProperties(prefix = PaymentProperties.PREFIX)
public class PaymentProperties {
    public static final String PREFIX = "payment";

    private String apiHost;

    private String notifyOpenid;

    public String getNotifyOpenid() {
        return notifyOpenid;
    }

    public PaymentProperties setNotifyOpenid(String notifyOpenid) {
        this.notifyOpenid = notifyOpenid;
        return this;
    }

    public String getApiHost() {
        return apiHost;
    }

    public PaymentProperties setApiHost(String apiHost) {
        this.apiHost = apiHost;
        return this;
    }
}
