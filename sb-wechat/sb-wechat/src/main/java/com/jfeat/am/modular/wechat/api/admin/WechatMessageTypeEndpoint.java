package com.jfeat.am.modular.wechat.api.admin;

import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.common.persistence.model.WechatMessageType;
import com.jfeat.am.core.support.BeanKit;
import com.jfeat.am.modular.system.service.TenantService;
import com.jfeat.am.modular.wechat.constant.WechatPermission;
import com.jfeat.am.modular.wechat.service.WechatMessageTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by jackyhuang on 2017/9/1.
 */
@RestController
@RequestMapping("/api/adm/wechat_message_types")
public class WechatMessageTypeEndpoint extends BaseController {

    @Resource
    private WechatMessageTypeService wechatMessageTypeService;
    @Resource
    TenantService tenantService;

    @GetMapping
    @Permission(WechatPermission.WECHAT_TEMPLATE_MESSAGE_VIEW)
    public Tip getMessageTypes() {
        return SuccessTip.create(wechatMessageTypeService.selectAll());
    }

    @GetMapping("/{id}")
    public Tip getMessageType(@PathVariable Long id) {
        WechatMessageType wechatMessageType = wechatMessageTypeService.getById(id);
        Map<String, Object> map = BeanKit.beanToMap(wechatMessageType);
        map.put("props", wechatMessageTypeService.getMessageTypePropsByTypeId(id));
        return SuccessTip.create(map);
    }
}
