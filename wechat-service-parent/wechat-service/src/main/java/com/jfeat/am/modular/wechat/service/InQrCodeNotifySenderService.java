package com.jfeat.am.modular.wechat.service;

/**
 * 扫码事件通知
 * @author jackyhuang
 * @date 2018/3/10
 */
public interface InQrCodeNotifySenderService {
    boolean subscribe(String appId, String openid, String eventKey, String ticket);
    boolean scan(String appId, String openid, String eventKey, String ticket);
}
