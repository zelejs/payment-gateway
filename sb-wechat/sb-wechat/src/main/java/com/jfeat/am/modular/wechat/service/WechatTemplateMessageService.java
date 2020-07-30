package com.jfeat.am.modular.wechat.service;

import com.jfeat.am.common.persistence.model.WechatField;
import com.jfeat.am.common.persistence.model.WechatTemplateMessage;
import com.jfeat.am.modular.wechat.wrapper.WechatTemplateMessageWrapper;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Silent-Y
 * @date 2017/8/24
 */
public interface WechatTemplateMessageService {

    public boolean send(Long tenantId, String openId, String messageTypeName, Map<String, String> params, String url);

    public List<WechatTemplateMessage> getTemplateMessages(Long tenantId, Long typeId);

    public WechatTemplateMessage getTemplateMessageById(Long id);

    public List<WechatField> getFieldsByTemplateMessageId(Long templateMessageId);

    public boolean deleteTemplateMessageById(Long id);

    public boolean createTemplateMessage(WechatTemplateMessageWrapper wechatTemplateMessageWrapper);

    public boolean updateTemplateMessage(WechatTemplateMessageWrapper wechatTemplateMessageWrapper);

}
