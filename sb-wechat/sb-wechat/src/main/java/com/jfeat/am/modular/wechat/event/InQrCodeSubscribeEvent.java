package com.jfeat.am.modular.wechat.event;

import com.jfeat.module.event.BasicEvent;

/**
 * @author jackyhuang
 * @date 2018/3/26
 */
public class InQrCodeSubscribeEvent extends BasicEvent<InQrCodeBean> {

    public InQrCodeSubscribeEvent(Object source, InQrCodeBean target) {
        super(source, target);
    }
}
