package com.jfeat.am.modular.wechat.service;

import com.jfeat.am.common.persistence.model.WechatMessageType;
import com.jfeat.am.common.persistence.model.WechatMessageTypeProp;

import java.util.List;

/**
 *
 * @author jackyhuang
 * @date 2017/9/1
 */
public interface WechatMessageTypeService {
    public List<WechatMessageType> selectAll();
    public WechatMessageType getById(Long id);
    public List<WechatMessageTypeProp> getMessageTypePropsByTypeId(Long typeId);
}
