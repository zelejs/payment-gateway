package com.jfeat.am.modular.wechat.bean;

/**
 * @author jackyhuang
 * @date 2018/3/12
 */
public class QrCode {
    private String url;
    private String ticket;
    private Integer expire_seconds;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(Integer expire_seconds) {
        this.expire_seconds = expire_seconds;
    }
}
