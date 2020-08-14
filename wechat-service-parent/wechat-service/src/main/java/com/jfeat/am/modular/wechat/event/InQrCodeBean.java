package com.jfeat.am.modular.wechat.event;

/**
 * @author jackyhuang
 * @date 2018/3/26
 */
public class InQrCodeBean {
    private String appId;
    private String openid;
    private String sceneId;
    private String ticket;

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

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
