package com.jfeat.am.modular.wechat.event;

import com.jfeat.module.event.BasicEvent;

/**
 * @author jackyhuang
 * @date 2018/3/26
 */
public class InFollowSubscribeEvent extends BasicEvent<InFollowBean> {
    public InFollowSubscribeEvent(Object source, InFollowBean target) {
        super(source, target);
    }
}
