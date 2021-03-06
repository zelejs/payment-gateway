package com.jfeat.am.modular.wechat.api.pub;

import com.google.common.collect.Maps;
import com.jfeat.am.modular.wechat.sdk.api.SnsApi;
import com.jfeat.am.modular.wechat.service.WechatConfigService;
import com.jfeat.am.modular.wechat.wrapper.WxAppLoginWrapper;
import com.jfeat.crud.base.tips.ErrorTip;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
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
 * 微信登录验证，返回accessToken
 * Created by jackyhuang on 2017/9/21.
 */
@RestController
@RequestMapping("/api/pub/wx")
public class WxLoginEndpoint   {

    @Resource
    private WechatConfigService wechatConfigService;

    //@Resource
    //private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(WechatPayNotifyEndpoint.class);

    @PostMapping("/login")
    public Tip login(@Valid @RequestBody WxAppLoginWrapper loginWrapper) {
        String openid = loginWrapper.getOpenid();
        //AuthResult authResult = SnsApi.validateAccessToken(loginWrapper.getAccessToken(), openid);
       /* if (!authResult.isSucceed()) {
            logger.error("wx login failed. code = {}, error = {}", authResult.getErrorCode(), authResult.getErrorMsg());
            return ErrorTip.create(BusinessCode.ThirdPartError);
        }*/

        //generate accessToken
     /*   User user = userService.getByOpenid(openid);
        if (user == null) {
            user = new User();
            user.setPassword(RandomStringUtils.randomAlphabetic(10));
            user.setAccount(openid);
            user.setOpenid(openid);
            user.setName(openid);
            userService.saveUser(user, null);
        }

        String token = JWTService.me().createToken(user.getTenantId(), user.getId(), user.getAccount());
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(token);
        accessToken.setExpiresIn(JWTService.me().getExpiresIn());
        accessToken.setTokenType(JWTService.me().getTokenType());*/

        Map<String, Object> result = Maps.newHashMap();
        /*result.put("loginName", user.getAccount());
        result.put("openid", openid);
        result.put("accessToken", accessToken);*/
        return SuccessTip.create(result);
    }

}
