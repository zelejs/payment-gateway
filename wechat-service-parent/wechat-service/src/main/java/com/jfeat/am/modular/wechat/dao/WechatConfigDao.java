package com.jfeat.am.modular.wechat.dao;

import com.jfeat.am.common.persistence.model.WechatConfig;
import org.apache.ibatis.annotations.Param;

/**
 * Created by jackyhuang on 2017/6/29.
 */
public interface WechatConfigDao {

    public WechatConfig findByTenantId(@Param("tenantId") Long tenantId);

    public WechatConfig findByAppId(@Param("appId") String appId);
}
