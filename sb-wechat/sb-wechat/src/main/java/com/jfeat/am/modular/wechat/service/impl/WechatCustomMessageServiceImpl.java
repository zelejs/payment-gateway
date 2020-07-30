package com.jfeat.am.modular.wechat.service.impl;

import com.jfeat.am.modular.wechat.service.WechatCustomMessageService;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.CustomServiceApi;
import com.jfinal.weixin.sdk.api.MediaArticles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jackyhuang
 * @date 2018/3/12
 */
@Service
public class WechatCustomMessageServiceImpl implements WechatCustomMessageService {

    private static final Logger logger = LoggerFactory.getLogger(WechatCustomMessageService.class);

    @Override
    public boolean sendNew(String openid, String title, String description, String url, String pictureUrl) {
        List<CustomServiceApi.Articles> articles = new ArrayList<>();
        CustomServiceApi.Articles article = new CustomServiceApi.Articles();
        article.setPicurl(pictureUrl);
        article.setTitle(title);
        article.setDescription(description);
        article.setUrl(url);
        articles.add(article);
        ApiResult apiResult = CustomServiceApi.sendNews(openid, articles);
        logger.debug("send custom msg result: {}", apiResult.getJson());
        return apiResult.isSucceed();
    }
}
