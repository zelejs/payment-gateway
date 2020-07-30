package com.jfeat.am.wechat;

import com.jfeat.am.base.BaseJunit;
import com.jfeat.am.modular.wechat.service.InQrCodeNotifySenderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jackyhuang
 * @date 2018/3/27
 */
public class InQrCodeNotifySenderServiceTest extends BaseJunit {

    @Autowired
    InQrCodeNotifySenderService inQrCodeNotifySenderService;

    @Test
    public void testNotifySubscribe() {
        inQrCodeNotifySenderService.subscribe("appid", "openid", "qrscene_973130635096899585", "ticket");
    }
}
