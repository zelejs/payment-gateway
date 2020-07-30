package com.jfeat.am.modular.wechat.event;

/**
 * @author jackyhuang
 * @date 2018/3/26
 */
public class PaidBean {
    private String orderNumber;
    private String paymentType;
    private String transactionId;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
