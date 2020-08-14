package com.jfeat.am.modular.wechat.event;
import com.jfeat.module.event.BasicEvent;

/**
 * @author jackyhuang
 * @date 2018/3/26
 */
public class PaidEvent extends BasicEvent<PaidBean>  {

    public PaidEvent(Object source, PaidBean target) {
        super(source, target);
    }
}
