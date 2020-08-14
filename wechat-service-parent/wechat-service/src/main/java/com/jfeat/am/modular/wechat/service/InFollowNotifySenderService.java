package com.jfeat.am.modular.wechat.service;

/**
 * 关注事件通知
 * @author jackyhuang
 * @date 2018/3/10
 */
public interface InFollowNotifySenderService {
    boolean subscribe(String appId, String openid);
    boolean unsubscribe(String appId, String openid);
}
