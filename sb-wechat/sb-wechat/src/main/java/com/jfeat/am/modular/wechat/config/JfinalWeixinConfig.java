package com.jfeat.am.modular.wechat.config;

import com.jfeat.am.core.util.SpringContextHolder;
import com.jfeat.am.modular.wechat.controller.WeixinMsgController;
import com.jfeat.am.modular.wechat.service.WechatConfigService;
import com.jfinal.config.*;
import com.jfinal.core.Controller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by jackyhuang on 2017/7/3.
 */
@Component
@DependsOn("springContextHolder")
public class JfinalWeixinConfig extends JFinalConfig {

    private WechatConfigService configService = SpringContextHolder.getBean(WechatConfigService.class);

    @Override
    public void configConstant(Constants constants) {

    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/wx/msg", WeixinMsgController.class);
        Map<String, Class<? extends Controller>> map = ConfigKit.me().getRoutes();
        for (Map.Entry<String, Class<? extends Controller>> entry : map.entrySet()) {
            routes.add(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void configPlugin(Plugins plugins) {

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }

    @Override
    public void afterJFinalStart() {
        configService.refreshApiConfig();
    }

    @Bean(initMethod = "init")
    public Object initApiConfig() {
        configService.refreshApiConfig();
        return null;
    }
}
