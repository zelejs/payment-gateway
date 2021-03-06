package com.jfeat.am.modular.wechat.api.admin;

import com.google.common.collect.Maps;
import com.jfeat.am.common.persistence.model.WechatTemplateMessage;
import com.jfeat.am.core.support.BeanKit;
import com.jfeat.am.modular.wechat.constant.WechatPermission;
import com.jfeat.am.modular.wechat.notification.AbstractNotification;
import com.jfeat.am.modular.wechat.notification.MessageNotification;
import com.jfeat.am.modular.wechat.service.WechatMessageTypeService;
import com.jfeat.am.modular.wechat.service.WechatTemplateMessageService;
import com.jfeat.am.modular.wechat.wrapper.WechatTemplateMessageWrapper;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by jackyhuang on 2017/6/18.
 */
@RestController
@RequestMapping("/api/adm/wechat_template_messages")
public class WechatTemplateMessageEndpoint   {

    @Resource
    private WechatTemplateMessageService wechatTemplateMessageService;

    @Resource
    private WechatMessageTypeService wechatMessageTypeService;

    @Resource
    private MessageNotification messageNotification;

    //@Resource
    //TenantService tenantService;

    @ApiOperation("微信模版消息列表")
    @GetMapping
    //@Permission({WechatPermission.WECHAT_TEMPLATE_MESSAGE_VIEW})
    public Tip list(@RequestParam(required = false) Long typeId) {
        //Long tenantId = tenantService.getDefaultTenant().getId();
        return SuccessTip.create(wechatTemplateMessageService.getTemplateMessages(null, typeId));
    }

    @ApiOperation("单个微信模版消息")
    @GetMapping("/{id}")
    //@Permission({WechatPermission.WECHAT_TEMPLATE_MESSAGE_VIEW})
    public Tip getTemplateMessageById(@PathVariable Long id) {
        WechatTemplateMessage wechatTemplateMessage = wechatTemplateMessageService.getTemplateMessageById(id);
        Map<String, Object> map = BeanKit.beanToMap(wechatTemplateMessage);
        map.put("props", wechatMessageTypeService.getMessageTypePropsByTypeId(wechatTemplateMessage.getTypeId()));
        map.put("fields", wechatTemplateMessageService.getFieldsByTemplateMessageId(wechatTemplateMessage.getId()));
        return SuccessTip.create(map);
    }

    @ApiOperation("添加微信模版消息")
    @PostMapping
    //@Permission({WechatPermission.WECHAT_TEMPLATE_MESSAGE_UPDATE})
    public Tip save(@Valid @RequestBody WechatTemplateMessageWrapper wechatTemplateMessageWrapper) {
        //Long tenantId = tenantService.getDefaultTenant().getId();
        wechatTemplateMessageWrapper.setTenantId(null);
        boolean result = wechatTemplateMessageService.createTemplateMessage(wechatTemplateMessageWrapper);
        return SuccessTip.create(result);
    }

    @ApiOperation("更新微信模版消息")
    @PutMapping("/{id}")
    //@Permission({WechatPermission.WECHAT_TEMPLATE_MESSAGE_UPDATE})
    public Tip update(@Valid @RequestBody WechatTemplateMessageWrapper wechatTemplateMessageWrapper,
                      @PathVariable("id") Long id) {
        //Long tenantId = tenantService.getDefaultTenant().getId();
        WechatTemplateMessage wechatTemplateMessage = wechatTemplateMessageService.getTemplateMessageById(id);
        if (wechatTemplateMessage == null) {
            throw new BusinessException(BusinessCode.InvalidKey);
        }
        wechatTemplateMessageWrapper.setTenantId(null);
        wechatTemplateMessageWrapper.setId(id);
        boolean result = wechatTemplateMessageService.updateTemplateMessage(wechatTemplateMessageWrapper);
        return SuccessTip.create(result);
    }

    @ApiOperation("删除微信模版消息")
    @DeleteMapping("/{id}")
    //@Permission({WechatPermission.WECHAT_TEMPLATE_MESSAGE_DELETE})
    public Tip remove(@PathVariable("id") Long id) {
        //Long tenantId = tenantService.getDefaultTenant().getId();
        WechatTemplateMessage wechatTemplateMessage = wechatTemplateMessageService.getTemplateMessageById(id);
        if (wechatTemplateMessage == null) {
            throw new BusinessException(BusinessCode.InvalidKey);
        }
        boolean result = wechatTemplateMessageService.deleteTemplateMessageById(id);
        return SuccessTip.create(result);
    }

    @GetMapping("/send/{openid}")
    //@Permission({WechatPermission.WECHAT_TEMPLATE_MESSAGE_VIEW})
    public Tip testSend(@PathVariable String openid) {
        //Long tenantId = tenantService.getDefaultTenant().getId();
        messageNotification.setTenantId(null).setOpenid(openid).send();
        return SuccessTip.create();
    }

}
