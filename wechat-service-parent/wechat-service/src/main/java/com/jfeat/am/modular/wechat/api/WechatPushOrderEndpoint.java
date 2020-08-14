package com.jfeat.am.modular.wechat.api;


import com.jfeat.am.modular.wechat.service.WechatConfigService;
import com.jfeat.am.modular.wechat.service.WechatPushOrderService;
import com.jfeat.am.modular.wechat.wrapper.PushOrderWrapper;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfinal.weixin.sdk.api.PaymentApi;
import com.jfinal.weixin.sdk.kit.IpKit;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Silent-Y on 2017/10/12.
 */
@RestController
@RequestMapping("/api/pub/wpay/push_order")
public class WechatPushOrderEndpoint   {

    @Resource
    WechatPushOrderService wechatPushOrderService;

    @PostMapping
    public Tip pushOrder(@RequestBody PushOrderWrapper pushOrderWrapper) {
        String ip = IpKit.getRealIp(null/*getHttpServletRequest()*/);

        Map<String, String> result = wechatPushOrderService.pushOrder(pushOrderWrapper.getTitle(),
                pushOrderWrapper.getDetail(),
                pushOrderWrapper.getOrderNum(),
                pushOrderWrapper.getTotalFee(),
                pushOrderWrapper.getOpenid(),
                ip,
                pushOrderWrapper.getApiHost(),
                pushOrderWrapper.getTradeType());
        return SuccessTip.create(result);
    }
}
