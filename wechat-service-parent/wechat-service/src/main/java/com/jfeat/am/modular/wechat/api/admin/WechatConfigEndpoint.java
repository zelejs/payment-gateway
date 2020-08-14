package com.jfeat.am.modular.wechat.api.admin;

import com.jfeat.am.common.persistence.model.WechatConfig;
import com.jfeat.am.modular.wechat.constant.WechatPermission;
import com.jfeat.am.modular.wechat.service.WechatConfigService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by jackyhuang on 2017/6/18.
 */
@RestController
@RequestMapping("/api/adm/wechat_config")
public class WechatConfigEndpoint   {

    @Autowired
    private WechatConfigService configService;
    //@Resource
    //TenantService tenantService;

    @ApiOperation("配置微信")
    @GetMapping
    //@Permission({WechatPermission.WECHAT_CONFIG_VIEW})
    public Tip show() {
        //Long tenantId = tenantService.getDefaultTenant().getId();
        WechatConfig wechatConfig = configService.getByTenantId(null/*tenantId*/);
        return SuccessTip.create(wechatConfig);
    }

    @ApiOperation("更新配置微信")
    @PostMapping
    //@Permission({WechatPermission.WECHAT_CONFIG_UPDATE})
    public Tip update(@RequestBody WechatConfig wechatConfig) {
        //Long tenantId = tenantService.getDefaultTenant().getId();
        WechatConfig originalWechatConfig = configService.getByTenantId(null/*tenantId*/);
        wechatConfig.setTenantId(null/*tenantId*/);
        wechatConfig.setId(originalWechatConfig.getId());
        configService.updateWechatConfig(wechatConfig);
        return SuccessTip.create();
    }
}
