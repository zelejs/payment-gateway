package com.jfeat.am.modular.wechat.constant;

import com.jfinal.weixin.sdk.api.PaymentApi;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jackyhuang
 * @date 2018/9/30
 */
public class TradeType {
    public static final String WX_TYPE = "WX";
    public static final String WXA_TYPE = "WXA";
    public static final String NATIVE_TYPE = "NATIVE";
    public static final String APP_TYPE = "APP";

    public static final Map<String, String> types = new HashMap<>();
    static {
        types.put(WX_TYPE, PaymentApi.TradeType.JSAPI.name());
        types.put(WXA_TYPE, PaymentApi.TradeType.JSAPI.name());
        types.put(NATIVE_TYPE, PaymentApi.TradeType.NATIVE.name());
        types.put(APP_TYPE, PaymentApi.TradeType.APP.name());
    }

    public static boolean contains(String type) {
        return types.containsKey(type);
    }
}
