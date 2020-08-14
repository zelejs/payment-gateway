package com.jfeat.am.modular.wechat.service;

/**
 *
 * @author jackyhuang
 * @date 2018/3/10
 */
public interface PaidNotifySenderService {
    boolean send(String orderNumber, String paymentType, String transactionId);
}
