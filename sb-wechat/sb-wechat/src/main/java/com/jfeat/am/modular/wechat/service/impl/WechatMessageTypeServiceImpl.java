package com.jfeat.am.modular.wechat.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfeat.am.common.persistence.dao.WechatMessageTypeMapper;
import com.jfeat.am.common.persistence.dao.WechatMessageTypePropMapper;
import com.jfeat.am.common.persistence.model.WechatMessageType;
import com.jfeat.am.common.persistence.model.WechatMessageTypeProp;
import com.jfeat.am.modular.wechat.service.WechatMessageTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jackyhuang
 * @date 2017/9/1
 */
@Service
public class WechatMessageTypeServiceImpl extends ServiceImpl<WechatMessageTypeMapper, WechatMessageType>
        implements WechatMessageTypeService {

    @Resource
    private WechatMessageTypePropMapper wechatMessageTypePropMapper;

    @Override
    public List<WechatMessageType> selectAll() {
        return selectList(new EntityWrapper<>());
    }

    @Override
    public WechatMessageType getById(Long id) {
        return selectById(id);
    }

    @Override
    public List<WechatMessageTypeProp> getMessageTypePropsByTypeId(Long typeId) {
        WechatMessageTypeProp entity = new WechatMessageTypeProp();
        entity.setTypeId(typeId);
        return wechatMessageTypePropMapper.selectList(new EntityWrapper<>(entity));
    }
}
