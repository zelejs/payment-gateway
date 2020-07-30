package com.jfeat.am.modular.wechat.wrapper;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by jackyhuang on 2017/9/21.
 */
public class WxAppLoginWrapper {
    private String appId;
    private String code;

    /**
     * openid, accessToken 公众号登录时用
     */
    private String openid;
    private String accessToken;

    public String getOpenid() {
        return openid;
    }

    public WxAppLoginWrapper setOpenid(String openid) {
        this.openid = openid;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public WxAppLoginWrapper setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
