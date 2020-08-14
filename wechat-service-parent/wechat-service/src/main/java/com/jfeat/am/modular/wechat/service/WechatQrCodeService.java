package com.jfeat.am.modular.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.jfeat.am.modular.wechat.bean.QrCode;

import java.util.Map;

/**
 * @author jackyhuang
 * @date 2018/3/12
 */
public interface WechatQrCodeService {
    QrCode createTemporary(Long tenantId, Integer expireSeconds, Integer sceneId);
    QrCode createPermanent(Long tenantId, String sceneStr);
    String getShowUrl(String ticket);
}
