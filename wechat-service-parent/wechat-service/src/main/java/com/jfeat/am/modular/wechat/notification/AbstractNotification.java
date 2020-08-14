/*
 *   Copyright (C) 2014-2016 www.kequandian.net
 *
 *    The program may be used and/or copied only with the written permission
 *    from www.kequandian.net or in accordance with the terms and
 *    conditions stipulated in the agreement/contract under which the program
 *    has been supplied.
 *
 *    All rights reserved.
 *
 */

package com.jfeat.am.modular.wechat.notification;

import com.google.common.collect.Maps;
import com.jfeat.am.modular.wechat.service.WechatTemplateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by jackyhuang on 17/2/15.
 */
public abstract class AbstractNotification {

    @Autowired
    private WechatTemplateMessageService wechatTemplateMessageService;

    private Long tenantId;
    private String openid;
    private String templateMessageName;
    private Map<String, String> params = Maps.newHashMap();
    private String url;

    public AbstractNotification() {

    }

    public AbstractNotification(String templateMessageName) {
        this.templateMessageName = templateMessageName;
    }

    public AbstractNotification(Long tenantId, String openid, String templateMessageName) {
        this.tenantId = tenantId;
        this.openid = openid;
        this.templateMessageName = templateMessageName;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public AbstractNotification setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public String getOpenid() {
        return openid;
    }

    public AbstractNotification setOpenid(String openid) {
        this.openid = openid;
        return this;
    }

    public String getTemplateMessageName() {
        return templateMessageName;
    }

    public AbstractNotification setTemplateMessageName(String templateMessageName) {
        this.templateMessageName = templateMessageName;
        return this;
    }

    public AbstractNotification setParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public AbstractNotification param(String key, String value) {
        this.params.put(key, value);
        return this;
    }

    public String getUrl() {
        return url;
    }

    public AbstractNotification setUrl(String url) {
        this.url = url;
        return this;
    }

    public void send() {
        wechatTemplateMessageService.send(tenantId, openid, templateMessageName, params, url);
    }
}
