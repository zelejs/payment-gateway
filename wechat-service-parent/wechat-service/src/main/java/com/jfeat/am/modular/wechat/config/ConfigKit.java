package com.jfeat.am.modular.wechat.config;

import com.google.common.collect.Maps;
import com.jfinal.core.Controller;

import java.util.Map;

/**
 * Created by jackyhuang on 2017/7/17.
 */
public class ConfigKit {
    private static ConfigKit me = new ConfigKit();
    public static ConfigKit me() {
        return me;
    }

    private ConfigKit() {

    }

    private Map<String, Class<? extends Controller>> routes = Maps.newConcurrentMap();

    public void addRoute(String key, Class<? extends Controller> clazz) {
        routes.put(key, clazz);
    }

    public Map<String, Class<? extends Controller>> getRoutes() {
        return routes;
    }
}
