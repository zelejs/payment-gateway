package com.jfeat.am.wechat;

import org.junit.Test;

/**
 * @author jackyhuang
 * @date 2018/3/27
 */
public class GeneralTest {

    private static final String QR_CODE_SCENE_PREFIX = "qrscene_";

    @Test
    public void test() {
        String eventKey = "qrscene_973130635096899585";
        String sceneId = eventKey.substring(QR_CODE_SCENE_PREFIX.length(), eventKey.length());
        System.out.println(sceneId);
    }
}
