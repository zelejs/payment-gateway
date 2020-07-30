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
@TableName("wechat_message_type_prop")
public class WechatMessageTypeProp extends Model<WechatMessageTypeProp> {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("type_id")
	private Long typeId;
	private String name;
	@TableField("display_name")
	private String displayName;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WechatMessageTypeProp{" +
			"id=" + id +
			", typeId=" + typeId +
			", name=" + name +
			", displayName=" + displayName +
			"}";
	}
}
