package com.jfeat.am.modular.wechat.service;

import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author Silent-Y
 * @date 2017/10/12
 */
public interface WechatPushOrderService {

    public Map<String, String> pushOrder(String title,
                         String detail,
                         String orderNum,
                         String totalFee,
                         String openid,
                         String ip,
                         String apiHost,
                         String tradeType);

    public boolean refund(String outTradeNo, String outRefundNo, BigDecimal totalFee, BigDecimal refundFee);
}
