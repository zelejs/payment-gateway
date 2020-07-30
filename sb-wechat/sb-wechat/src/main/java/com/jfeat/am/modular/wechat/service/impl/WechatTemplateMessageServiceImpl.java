package com.jfeat.am.modular.wechat.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfeat.am.common.persistence.dao.WechatFieldMapper;
import com.jfeat.am.common.persistence.dao.WechatMessageTypeMapper;
import com.jfeat.am.common.persistence.dao.WechatMessageTypePropMapper;
import com.jfeat.am.common.persistence.dao.WechatTemplateMessageMapper;
import com.jfeat.am.common.persistence.model.*;
import com.jfeat.am.core.support.BeanKit;
import com.jfeat.am.core.support.StrKit;
import com.jfeat.am.modular.wechat.constant.Const;
import com.jfeat.am.modular.wechat.service.WechatConfigService;
import com.jfeat.am.modular.wechat.service.WechatTemplateMessageService;
import com.jfeat.am.modular.wechat.wrapper.WechatFieldWrapper;
import com.jfeat.am.modular.wechat.wrapper.WechatTemplateMessageWrapper;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.TemplateData;
import com.jfinal.weixin.sdk.api.TemplateMsgApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Silent-Y
 * @date 2017/8/24
 */
@Service
public class WechatTemplateMessageServiceImpl extends ServiceImpl<WechatTemplateMessageMapper, WechatTemplateMessage>
        implements WechatTemplateMessageService {

    private static final Logger logger = LoggerFactory.getLogger(WechatTemplateMessageService.class);

    @Resource
    private WechatMessageTypeMapper wechatMessageTypeMapper;
    @Resource
    private WechatTemplateMessageMapper wechatTemplateMessageMapper;
    @Resource
    private WechatMessageTypePropMapper wechatMessageTypePropMapper;
    @Resource
    private WechatFieldMapper wechatFieldMapper;
    @Resource
    private WechatConfigService wechatConfigService;


    public List<WechatTemplateMessage> getTemplateMessages(Long tenantId, Long typeId) {
        WechatTemplateMessage entity = new WechatTemplateMessage();
        entity.setTenantId(tenantId);
        if (typeId != null) {
            entity.setTypeId(typeId);
        }
        return selectList(new EntityWrapper<>(entity));
    }

    public WechatTemplateMessage getTemplateMessageById(Long id) {
        return selectById(id);
    }

    @Override
    public List<WechatField> getFieldsByTemplateMessageId(Long templateMessageId) {
        WechatField entity = new WechatField();
        entity.setTemplateMessageId(templateMessageId);
        return wechatFieldMapper.selectList(new EntityWrapper<>(entity));
    }

    private void disableAll(Long tenantId, Long typeId) {
        List<WechatTemplateMessage> list = getTemplateMessages(tenantId, typeId);
        for (WechatTemplateMessage templateMessage : list) {
            templateMessage.setEnabled(Const.TEMPLATE_MESSAGE_DISABLED);
        }
        if (list.size() > 0) {
            updateBatchById(list);
        }
    }

    @Transactional
    public boolean createTemplateMessage(WechatTemplateMessageWrapper wechatTemplateMessageWrapper) {
        if (wechatTemplateMessageWrapper.getEnabled() == Const.TEMPLATE_MESSAGE_ENABLED) {
            disableAll(wechatTemplateMessageWrapper.getTenantId(), wechatTemplateMessageWrapper.getTypeId());
        }
        WechatTemplateMessage templateMessage = new WechatTemplateMessage();
        BeanKit.copyProperties(wechatTemplateMessageWrapper, templateMessage);
        insert(templateMessage);
        Long templateMessageId = templateMessage.getId();
        List<WechatFieldWrapper> wechatFieldWrappers = wechatTemplateMessageWrapper.getFields();
        for (WechatFieldWrapper wechatFieldWrapper : wechatFieldWrappers) {
            WechatField field = new WechatField();
            field.setPropId(wechatFieldWrapper.getPropId());
            field.setTemplateMessageId(templateMessageId);
            field.setName(wechatFieldWrapper.getName());
            field.setDisplayValue(wechatFieldWrapper.getDisplayValue());
            wechatFieldMapper.insert(field);
        }
        return true;
    }

    @Transactional
    public boolean updateTemplateMessage(WechatTemplateMessageWrapper wechatTemplateMessageWrapper) {
        if (wechatTemplateMessageWrapper.getEnabled() == Const.TEMPLATE_MESSAGE_ENABLED) {
            disableAll(wechatTemplateMessageWrapper.getTenantId(), wechatTemplateMessageWrapper.getTypeId());
        }
        WechatTemplateMessage templateMessage = new WechatTemplateMessage();
        BeanKit.copyProperties(wechatTemplateMessageWrapper, templateMessage);
        wechatTemplateMessageMapper.updateById(templateMessage);

        WechatField entity = new WechatField();
        entity.setTemplateMessageId(templateMessage.getId());
        wechatFieldMapper.delete(new EntityWrapper<>(entity));

        List<WechatFieldWrapper> wechatFieldWrappers = wechatTemplateMessageWrapper.getFields();
        for (WechatFieldWrapper wechatFieldWrapper : wechatFieldWrappers) {
            WechatField field = new WechatField();
            field.setPropId(wechatFieldWrapper.getPropId());
            field.setTemplateMessageId(templateMessage.getId());
            field.setName(wechatFieldWrapper.getName());
            field.setDisplayValue(wechatFieldWrapper.getDisplayValue());
            wechatFieldMapper.insert(field);
        }
        return true;
    }

    @Transactional
    public boolean deleteTemplateMessageById(Long id) {
        wechatTemplateMessageMapper.deleteById(id);
        WechatField entity = new WechatField();
        entity.setTemplateMessageId(id);
        wechatFieldMapper.delete(new EntityWrapper<>(entity));
        return true;
    }

    public boolean send(Long tenantId, String openId, String messageTypeName, Map<String, String> params, String url) {
        if (StrKit.isBlank(openId) || tenantId == null|| params == null) {
            logger.debug("invalid openid {} or tenantid {}, params {}.", openId, tenantId, params);
            return false;
        }

        WechatMessageType wechatMessageType = new WechatMessageType();
        wechatMessageType.setName(messageTypeName);
        WechatMessageType wechatMessageTypeResult = wechatMessageTypeMapper.selectOne(wechatMessageType);

        WechatTemplateMessage wechatTemplateMessage = new WechatTemplateMessage();
        wechatTemplateMessage.setTypeId(wechatMessageTypeResult.getId());
        wechatTemplateMessage.setEnabled(Const.TEMPLATE_MESSAGE_ENABLED);
        WechatTemplateMessage wechatTemplateMessageResult = wechatTemplateMessageMapper.selectOne(wechatTemplateMessage);
        if (wechatTemplateMessageResult == null) {
            return false;
        }
        TemplateData templateData = TemplateData.New()
                // 消息接收者
                .setTouser(openId)
                .setTemplate_id(wechatTemplateMessageResult.getTemplateId());
        if (url != null) {
            templateData.setUrl(url);
        }
        for (Map.Entry<String, String> entry : params.entrySet()) {
            WechatMessageTypeProp wechatMessageTypeProp = new WechatMessageTypeProp();
            wechatMessageTypeProp.setTypeId(wechatMessageTypeResult.getId());
            wechatMessageTypeProp.setName(entry.getKey());
            WechatMessageTypeProp wechatMessageTypePropResult = wechatMessageTypePropMapper.selectOne(wechatMessageTypeProp);
            if (wechatMessageTypePropResult == null) {
                continue;
            }
            WechatField wechatField = new WechatField();
            wechatField.setPropId(wechatMessageTypePropResult.getId());
            wechatField.setTemplateMessageId(wechatTemplateMessageResult.getId());
            WechatField wechatFieldResult = wechatFieldMapper.selectOne(wechatField);
            if (wechatFieldResult != null) {
                String keyToSend = wechatFieldResult.getName();
                if (StrKit.notBlank(wechatFieldResult.getDisplayValue())) {
                    templateData.add(keyToSend, wechatFieldResult.getDisplayValue(), "#999");
                } else {
                    templateData.add(keyToSend, entry.getValue(), "#999");
                }
            }
        }
        logger.debug("templateData:" + templateData.build());

        WechatConfig wechatConfig = wechatConfigService.getByTenantId(tenantId);
        ApiConfigKit.setThreadLocalAppId(wechatConfig.getAppId());
        ApiResult result = TemplateMsgApi.send(templateData.build());
        logger.debug("result:" + result.getJson());
        return result.isSucceed();
    }
}
