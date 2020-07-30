package com.jfeat.am.modular.wechat.wrapper;

/**
 * Created by Silent-Y on 2017/8/23.
 */
public class WechatFieldWrapper {

    private Long propId;
    private String name;
    private String displayValue;

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public Long getPropId() {
        return propId;
    }

    public void setPropId(Long propId) {
        this.propId = propId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
