package com.jfeat.am.modular.wechat.wrapper;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Silent-Y on 2017/8/23.
 */
public class WechatTemplateMessageWrapper {

    private Long id;

    @NotEmpty
    private String templateId;

    private Long tenantId;

    @NotNull
    private Long typeId;

    @NotBlank
    private String name;

    @Range(min = 0, max =  1)
    private Integer enabled;

    @NotEmpty
    private List<WechatFieldWrapper> fields;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public List<WechatFieldWrapper> getFields() {
        return fields;
    }

    public void setFields(List<WechatFieldWrapper> fields) {
        this.fields = fields;
    }
}
