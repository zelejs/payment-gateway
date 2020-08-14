package com.jfeat.am.modular.wechat.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.persistence.dao.WechatConfigMapper;
import com.jfeat.am.common.persistence.model.WechatConfig;
import com.jfeat.am.modular.wechat.service.WechatConfigService;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author jackyhuang
 * @date 2017/6/29
 */
@Service
public class WechatConfigServiceImpl implements WechatConfigService {

    @Autowired
    private WechatConfigMapper wechatConfigMapper;

    @Override
    public WechatConfig getByTenantId(Long tenantId) {
        WechatConfig query = new WechatConfig();
        query.setTenantId(tenantId);
        WechatConfig wechatConfig = wechatConfigMapper.selectOne(query);
        if (wechatConfig == null) {
            wechatConfig = new WechatConfig();
            wechatConfig.setTenantId(tenantId);
            wechatConfigMapper.insert(wechatConfig);
        }
        return wechatConfig;
    }

    @Override
    public WechatConfig getByAppId(String appId) {
        WechatConfig query = new WechatConfig();
        query.setAppId(appId);
        return wechatConfigMapper.selectOne(query);
    }

    @Override
    public WechatConfig getByWxaAppId(String appId) {
        WechatConfig query = new WechatConfig();
        query.setWxaAppId(appId);
        return wechatConfigMapper.selectOne(query);
    }

    @Override
    public boolean updateWechatConfig(WechatConfig wechatConfig) {
        wechatConfigMapper.updateById(wechatConfig);
        refreshApiConfig();
        return true;
    }

    @Override
    public void refreshApiConfig() {
        for (WechatConfig wechatConfig : wechatConfigMapper.selectList(new EntityWrapper<>())) {
            if (StrKit.notBlank(wechatConfig.getAppId())) {
                ApiConfig ac = new ApiConfig();
                // 配置微信 API 相关参数
                ac.setToken(wechatConfig.getToken());
                ac.setAppId(wechatConfig.getAppId());
                ac.setAppSecret(wechatConfig.getAppSecret());

                /**
                 *  是否对消息进行加密，对应于微信平台的消息加解密方式：
                 *  1：true进行加密且必须配置 encodingAesKey
                 *  2：false采用明文模式，同时也支持混合模式
                 */
                ac.setEncryptMessage(wechatConfig.getEncryptMessage() == 1);
                ac.setEncodingAesKey(wechatConfig.getEncodingAesKey());

                /**
                 * 多个公众号时，重复调用ApiConfigKit.putApiConfig(ac)依次添加即可，第一个添加的是默认。
                 */
                ApiConfigKit.putApiConfig(ac);
            }
        }
    }
}
