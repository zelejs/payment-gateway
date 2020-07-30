package com.jfeat.am.modular.wechat.event;

import com.jfeat.module.event.BasicEvent;

import java.math.BigDecimal;

/**
 * @author jackyhuang
 * @date 2018/3/26
 */
public class LocationUpdateBean {

    private String appId;
    private String openid;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal precision;

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

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getPrecision() {
        return precision;
    }

    public void setPrecision(BigDecimal precision) {
        this.precision = precision;
    }
}
