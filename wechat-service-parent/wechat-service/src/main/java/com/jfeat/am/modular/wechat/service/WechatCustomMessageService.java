package com.jfeat.am.modular.wechat.service;

/**
 * @author jackyhuang
 * @date 2018/3/12
 */
public interface WechatCustomMessageService {
    boolean sendNew(String openid, String title, String description, String url, String pictureUrl);
}
