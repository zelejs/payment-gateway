package com.jfeat.am.modular.wechat.event;

import com.jfeat.module.event.BasicEvent;

/**
 * @author jackyhuang
 * @date 2018/3/26
 */
public class InFollowUnsubscribeEvent extends BasicEvent<InFollowBean> {
    public InFollowUnsubscribeEvent(Object source, InFollowBean target) {
        super(source, target);
    }
}
