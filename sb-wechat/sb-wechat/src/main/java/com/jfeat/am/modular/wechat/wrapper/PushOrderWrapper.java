package com.jfeat.am.modular.wechat.wrapper;

/**
 * Created by Silent-Y on 2017/10/12.
 */
public class PushOrderWrapper {
    private String title;
    private String detail;
    private String openid;
    private String orderNum;
    private String totalFee;
    private String apiHost;
    private String tradeType = "WX";

    public String getTradeType() {
        return tradeType;
    }

    public PushOrderWrapper setTradeType(String tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public String getOpenid() {
        return openid;
    }

    public PushOrderWrapper setOpenid(String openid) {
        this.openid = openid;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getApiHost() {
        return apiHost;
    }

    public PushOrderWrapper setApiHost(String apiHost) {
        this.apiHost = apiHost;
        return this;
    }
}
