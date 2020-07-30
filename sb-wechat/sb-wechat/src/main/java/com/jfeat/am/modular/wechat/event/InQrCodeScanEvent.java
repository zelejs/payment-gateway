package com.jfeat.am.modular.wechat.event;

import com.jfeat.module.event.BasicEvent;

/**
 * @author jackyhuang
 * @date 2018/3/26
 */
public class InQrCodeScanEvent extends BasicEvent<InQrCodeBean> {

    public InQrCodeScanEvent(Object source, InQrCodeBean target) {
        super(source, target);
    }
}
