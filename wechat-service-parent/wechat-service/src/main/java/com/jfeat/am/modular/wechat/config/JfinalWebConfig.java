package com.jfeat.am.modular.wechat.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jackyhuang on 2017/7/3.
 */
@Configuration
public class JfinalWebConfig {

    @Bean
    public FilterRegistrationBean jFinalFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new JfinalWeixinFilter());
        registration.setOrder(Integer.MAX_VALUE - 1);
        registration.addUrlPatterns("/*");
        registration.addInitParameter("configClass", JfinalWeixinConfig.class.getName());
        return registration;
    }
}
