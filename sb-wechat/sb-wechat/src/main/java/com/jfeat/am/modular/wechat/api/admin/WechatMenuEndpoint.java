package com.jfeat.am.modular.wechat.api.admin;

import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.modular.wechat.constant.WechatPermission;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 微信公众号菜单管理
 * Created by jackyhuang on 2017/6/18.
 */
@RestController
@RequestMapping("/api/adm/wechat_menu")
public class WechatMenuEndpoint extends BaseController {

    @ApiOperation("查询微信菜单")
    @GetMapping
    @Permission({WechatPermission.WECHAT_MENU_VIEW})
    public Tip get() {
        //TODO

        return ErrorTip.create(500, "Not implemented");
    }

    @ApiOperation("更新微信菜单")
    @PostMapping
    @Permission({WechatPermission.WECHAT_MENU_UPDATE})
    public Tip update() {
        //TODO

        return ErrorTip.create(500, "Not implemented");
    }
}
