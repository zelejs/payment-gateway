package com.jfeat.am.modular.wechat.event;

import com.jfeat.module.event.BasicEvent;

/**
 * @author jackyhuang
 * @date 2018/3/26
 */
public class LocationUpdateEvent extends BasicEvent<LocationUpdateBean> {

    public LocationUpdateEvent(Object source, LocationUpdateBean target) {
        super(source, target);
    }
}
