package com.jfeat.am.modular.wechat.service;

import java.math.BigDecimal;

/**
 * 地理位置更新通知
 * @author jackyhuang
 * @date 2018/3/10
 */
public interface LocationUpdateNotifySenderService {
    boolean send(String appId, String openid, BigDecimal latitude, BigDecimal longitude, BigDecimal precision);
}
