package com.jfeat.am.modular.wechat.service.impl;

import com.jfeat.am.modular.wechat.event.InFollowBean;
import com.jfeat.am.modular.wechat.event.InFollowSubscribeEvent;
import com.jfeat.am.modular.wechat.event.InFollowUnsubscribeEvent;
import com.jfeat.am.modular.wechat.service.InFollowNotifySenderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 关注/取消关注事件通知默认实现类
 * 会通知通过 NotifySenderHandlerService 注册上来的处理器
 *
 * @author jackyhuang
 * @date 2018/3/11
 */
@Service
public class InFollowNotifySenderServiceImpl implements InFollowNotifySenderService {

    //@Resource
    //EventService eventService;

    @Override
    public boolean subscribe(String appId, String openid) {
        InFollowBean inFollowBean = new InFollowBean();
        inFollowBean.setAppId(appId);
        inFollowBean.setOpenid(openid);
        //eventService.publishEvent(new InFollowSubscribeEvent(this, inFollowBean));
        return true;
    }

    @Override
    public boolean unsubscribe(String appId, String openid) {
        InFollowBean inFollowBean = new InFollowBean();
        inFollowBean.setAppId(appId);
        inFollowBean.setOpenid(openid);
        //eventService.publishEvent(new InFollowUnsubscribeEvent(this, inFollowBean));
        return true;
    }
}
