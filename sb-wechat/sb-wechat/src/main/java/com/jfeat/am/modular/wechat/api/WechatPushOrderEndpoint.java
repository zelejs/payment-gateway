package com.jfeat.am.modular.wechat.api;


import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.common.persistence.model.User;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.modular.system.service.UserService;
import com.jfeat.am.modular.wechat.service.WechatConfigService;
import com.jfeat.am.modular.wechat.service.WechatPushOrderService;
import com.jfeat.am.modular.wechat.wrapper.PushOrderWrapper;
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
public class WechatPushOrderEndpoint extends BaseController {

    @Resource
    WechatPushOrderService wechatPushOrderService;

    @PostMapping
    public Tip pushOrder(@RequestBody PushOrderWrapper pushOrderWrapper) {
        String ip = IpKit.getRealIp(getHttpServletRequest());

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
