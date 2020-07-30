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
@TableName("wechat_field")
public class WechatField extends Model<WechatField> {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("prop_id")
	private Long propId;
	@TableField("template_message_id")
	private Long templateMessageId;
	private String name;
	@TableField("display_value")
	private String displayValue;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPropId() {
		return propId;
	}

	public void setPropId(Long propId) {
		this.propId = propId;
	}

	public Long getTemplateMessageId() {
		return templateMessageId;
	}

	public void setTemplateMessageId(Long templateMessageId) {
		this.templateMessageId = templateMessageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WechatField{" +
			"id=" + id +
			", propId=" + propId +
			", templateMessageId=" + templateMessageId +
			", name=" + name +
			", displayValue=" + displayValue +
			"}";
	}
}
