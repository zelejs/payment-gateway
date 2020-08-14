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
@TableName("wechat_config")
public class WechatConfig extends Model<WechatConfig> {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("tenant_id")
	private Long tenantId;
	@TableField("app_id")
	private String appId;
	@TableField("app_secret")
	private String appSecret;
	private String token;
	@TableField("encrypt_message")
	private Integer encryptMessage;
	@TableField("encoding_aes_key")
	private String encodingAesKey;
	@TableField("partner_id")
	private String partnerId;
	@TableField("partner_key")
	private String partnerKey;
	private String host;
	@TableField("wxa_app_id")
	private String wxaAppId;
	@TableField("wxa_app_secret")
	private String wxaAppSecret;
	@TableField("cert_path")
	private String certPath;

	public String getCertPath() {
		return certPath;
	}

	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}

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

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getEncryptMessage() {
		return encryptMessage;
	}

	public void setEncryptMessage(Integer encryptMessage) {
		this.encryptMessage = encryptMessage;
	}

	public String getEncodingAesKey() {
		return encodingAesKey;
	}

	public void setEncodingAesKey(String encodingAesKey) {
		this.encodingAesKey = encodingAesKey;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerKey() {
		return partnerKey;
	}

	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getWxaAppId() {
		return wxaAppId;
	}

	public void setWxaAppId(String wxaAppId) {
		this.wxaAppId = wxaAppId;
	}

	public String getWxaAppSecret() {
		return wxaAppSecret;
	}

	public void setWxaAppSecret(String wxaAppSecret) {
		this.wxaAppSecret = wxaAppSecret;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WechatConfig{" +
			"id=" + id +
			", tenantId=" + tenantId +
			", appId=" + appId +
			", appSecret=" + appSecret +
			", token=" + token +
			", encryptMessage=" + encryptMessage +
			", encodingAesKey=" + encodingAesKey +
			", partnerId=" + partnerId +
			", partnerKey=" + partnerKey +
			", host=" + host +
			", wxaAppId=" + wxaAppId +
			", wxaAppSecret=" + wxaAppSecret +
			"}";
	}
}
