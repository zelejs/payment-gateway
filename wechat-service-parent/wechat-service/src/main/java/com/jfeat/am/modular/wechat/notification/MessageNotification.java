package com.jfeat.am.modular.wechat.notification;

import org.springframework.stereotype.Component;

/**
 * Created by jackyhuang on 2017/9/25.
 */
@Component
public class MessageNotification extends AbstractNotification {
    private static final String messageType = "message-notification";

    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String REMARK = "remark";

    private static final String title = "通知";
    private static final String content = "";
    private static final String remark = "";

    public MessageNotification() {
        super(messageType);
        param(TITLE, title).param(CONTENT, content).param(REMARK, remark);
    }

    public MessageNotification setTitle(String title) {
        param(TITLE, title);
        return this;
    }

    public MessageNotification setContent(String content) {
        param(CONTENT, content);
        return this;
    }

    public MessageNotification setRemark(String remark) {
        param(REMARK, remark);
        return this;
    }
}
