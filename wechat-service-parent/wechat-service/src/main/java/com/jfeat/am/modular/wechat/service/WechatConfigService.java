package com.jfeat.am.modular.wechat.service;

import com.jfeat.am.common.persistence.model.WechatConfig;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author jackyhuang
 * @date 2017/6/28
 */
public interface WechatConfigService {

    WechatConfig getByTenantId(Long tenantId);

    WechatConfig getByAppId(String appId);

    WechatConfig getByWxaAppId(String appId);

    boolean updateWechatConfig(WechatConfig wechatConfig);

    void refreshApiConfig();

}
