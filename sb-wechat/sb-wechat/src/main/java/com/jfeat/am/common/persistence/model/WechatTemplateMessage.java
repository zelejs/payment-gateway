package com.jfeat.am.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2017-09-21
 */
@TableName("wechat_template_message")
public class WechatTemplateMessage extends Model<WechatTemplateMessage> {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("tenant_id")
	private Long tenantId;
	@TableField("type_id")
	private Long typeId;
	@TableField("template_id")
	private String templateId;
	private String name;
	private Integer enabled;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WechatTemplateMessage{" +
			"id=" + id +
			", tenantId=" + tenantId +
			", typeId=" + typeId +
			", templateId=" + templateId +
			", name=" + name +
			", enabled=" + enabled +
			"}";
	}
}
