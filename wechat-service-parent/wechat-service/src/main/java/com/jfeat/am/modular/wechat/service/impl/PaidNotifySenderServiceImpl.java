package com.jfeat.am.modular.wechat.service.impl;

import com.jfeat.am.modular.wechat.event.PaidBean;
import com.jfeat.am.modular.wechat.event.PaidEvent;
import com.jfeat.am.modular.wechat.service.NotifySenderExtension;
import com.jfeat.am.modular.wechat.service.PaidNotifySenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 如果没有配置 MQ 的实现，则用这个默认实现
 * @author jackyhuang
 * @date 2018/3/10
 */
@Service
@ConditionalOnMissingBean(NotifySenderExtension.class)
public class PaidNotifySenderServiceImpl implements PaidNotifySenderService {
    private static final Logger logger = LoggerFactory.getLogger(PaidNotifySenderServiceImpl.class);

    //@Resource
    //EventService eventService;

    @Override
    public boolean send(String orderNumber, String paymentType, String transactionId) {
        logger.debug("default impl.");
        PaidBean paidBean = new PaidBean();
        paidBean.setOrderNumber(orderNumber);
        paidBean.setPaymentType(paymentType);
        paidBean.setTransactionId(transactionId);
        //eventService.publishEvent(new PaidEvent(this, paidBean));
        return true;
    }
}
