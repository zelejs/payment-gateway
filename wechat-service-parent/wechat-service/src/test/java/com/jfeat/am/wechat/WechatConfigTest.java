package com.jfeat.am.wechat;

import com.jfeat.am.base.BaseJunit;
import com.jfeat.am.common.persistence.dao.WechatConfigMapper;
import com.jfeat.am.common.persistence.model.WechatConfig;
import com.jfeat.am.modular.wechat.service.WechatConfigService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by jackyhuang on 2017/6/29.
 */
public class WechatConfigTest extends BaseJunit {

    @Autowired
    private WechatConfigService configService;

    @Autowired
    private WechatConfigMapper wechatConfigMapper;

    private WechatConfig preparedConfig;

    @Before
    public void setup() {
        preparedConfig = new WechatConfig();
        preparedConfig.setTenantId(11111L);
        preparedConfig.setAppId("abc");
        preparedConfig.setToken("token");
        preparedConfig.setAppSecret("secret");
        preparedConfig.setEncodingAesKey("key");
        wechatConfigMapper.insert(preparedConfig);
    }

    @Test
    public void testFindByTenantId() {
        WechatConfig wechatConfig = configService.getByTenantId(preparedConfig.getTenantId());
        assertNotNull(wechatConfig);
        assertEquals("abc", wechatConfig.getAppId());

        wechatConfig = configService.getByTenantId(99999L);
        assertNotNull(wechatConfig);
    }

    @Test
    public void testUpdate() {
        WechatConfig wechatConfig = wechatConfigMapper.selectById(preparedConfig.getId());
        assertNotNull(wechatConfig);
        wechatConfig.setAppSecret("secrec");
        boolean result = configService.updateWechatConfig(wechatConfig);
        assertTrue(result);
    }
}
