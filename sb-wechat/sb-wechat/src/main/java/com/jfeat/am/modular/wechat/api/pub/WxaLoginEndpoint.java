package com.jfeat.am.modular.wechat.api.pub;

import com.google.common.collect.Maps;
import com.jfeat.am.common.persistence.model.WechatConfig;
import com.jfeat.am.modular.wechat.service.WechatConfigService;
import com.jfeat.am.modular.wechat.wrapper.WxAppLoginWrapper;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.tips.ErrorTip;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfinal.kit.JsonKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.wxaapp.WxaConfig;
import com.jfinal.wxaapp.WxaConfigKit;
import com.jfinal.wxaapp.api.WxaUserApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by jackyhuang on 2017/9/21.
 */
@RestController
@RequestMapping("/api/pub/wxa")
public class WxaLoginEndpoint   {

    @Resource
    private WechatConfigService wechatConfigService;

    //@Resource
    //private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(WxaLoginEndpoint.class);

    @PostMapping("/login")
    public Tip login(@Valid @RequestBody WxAppLoginWrapper loginWrapper) {
        WxaConfig wxaConfig = new WxaConfig();
        WechatConfig wechatConfig = wechatConfigService.getByWxaAppId(loginWrapper.getAppId());
        wxaConfig.setAppId(wechatConfig.getWxaAppId());
        wxaConfig.setAppSecret(wechatConfig.getWxaAppSecret());
        WxaConfigKit.setWxaConfig(wxaConfig);

        Long tenantId = wechatConfig.getTenantId();

        WxaUserApi wxaUserApi = new WxaUserApi();
        ApiResult apiResult = wxaUserApi.getSessionKey(loginWrapper.getCode());
        logger.debug(JsonKit.toJson(apiResult));
        if (!apiResult.isSucceed()) {
            return ErrorTip.create(BusinessCode.LoginFailure.getCode(), apiResult.getErrorMsg());
        }

        String openid = apiResult.get("openid");
        String sessionKey = apiResult.get("session_key");

        //generate accessToken
        /*User user = userService.getByOpenid(openid);
        if (user == null) {
            user = new User();
            user.setPassword(RandomStringUtils.randomAlphabetic(10));
            user.setAccount(openid);
            user.setOpenid(openid);
            user.setTenantId(tenantId);
            user.setName(openid);
            userService.saveUser(user, null);
        }

        String token = JWTService.me().createToken(tenantId, user.getId(), user.getAccount());
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(token);
        accessToken.setExpiresIn(JWTService.me().getExpiresIn());
        accessToken.setTokenType(JWTService.me().getTokenType());*/

        Map<String, Object> result = Maps.newHashMap();
        /*
        result.put("openid", openid);
        result.put("accessToken", accessToken);*/
        return SuccessTip.create(result);
    }

}
