package com.jfeat.am.modular.wechat.event;

/**
 * @author jackyhuang
 * @date 2018/3/26
 */
public class InFollowBean {
    private String appId;
    private String openid;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
