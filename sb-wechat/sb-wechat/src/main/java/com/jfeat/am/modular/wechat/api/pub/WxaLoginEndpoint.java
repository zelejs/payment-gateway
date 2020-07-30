package com.jfeat.am.modular.wechat.api.pub;

import com.google.common.collect.Maps;
import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.common.exception.BizExceptionEnum;
import com.jfeat.am.common.persistence.model.User;
import com.jfeat.am.common.persistence.model.WechatConfig;
import com.jfeat.am.core.jwt.JWTService;
import com.jfeat.am.core.shiro.AccessToken;
import com.jfeat.am.core.shiro.AccessTokenToken;
import com.jfeat.am.core.shiro.ShiroKit;
import com.jfeat.am.core.shiro.ShiroUser;
import com.jfeat.am.core.util.JsonKit;
import com.jfeat.am.modular.system.service.UserService;
import com.jfeat.am.modular.wechat.service.WechatConfigService;
import com.jfeat.am.modular.wechat.wrapper.WxAppLoginWrapper;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.wxaapp.WxaConfig;
import com.jfinal.wxaapp.WxaConfigKit;
import com.jfinal.wxaapp.api.WxaUserApi;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
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
public class WxaLoginEndpoint extends BaseController {

    @Resource
    private WechatConfigService wechatConfigService;

    @Resource
    private UserService userService;

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
            return ErrorTip.create(BizExceptionEnum.LOGIN_FAIL.getCode(), apiResult.getErrorMsg());
        }

        String openid = apiResult.get("openid");
        String sessionKey = apiResult.get("session_key");

        //generate accessToken
        User user = userService.getByOpenid(openid);
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
        accessToken.setTokenType(JWTService.me().getTokenType());

        Map<String, Object> result = Maps.newHashMap();
        result.put("openid", openid);
        result.put("accessToken", accessToken);
        return SuccessTip.create(result);
    }

}
