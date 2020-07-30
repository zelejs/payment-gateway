package com.jfeat.am.modular.wechat.api;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.common.persistence.model.WechatConfig;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.modular.wechat.service.WechatQrCodeService;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.QrcodeApi;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author jackyhuang
 * @date 2018/3/12
 */
@RestController
@RequestMapping("/api/wechat_qrcode")
public class WechatQrCodeEndpoint extends BaseController {

    @Autowired
    private WechatQrCodeService wechatQrCodeService;

    @ApiOperation(value = "生成带参数的临时二维码", notes = "最大30天，默认7天")
    @PostMapping("/temporary")
    public Tip createTemporary(@Max(2592000) @RequestParam(required = false, defaultValue = "604800") Integer expireSeconds,
                               @Min(1) @RequestParam Integer sceneId) {
        Long tenantId = JWTKit.getTenantId(getHttpServletRequest());
        return SuccessTip.create(wechatQrCodeService.createTemporary(tenantId, expireSeconds, sceneId));
    }

    @ApiOperation(value = "生成带参数的永久二维码")
    @PostMapping("/permanent")
    public Tip createPermanent(@Size(min = 1, max = 64) @RequestParam String sceneStr) {
        Long tenantId = JWTKit.getTenantId(getHttpServletRequest());
        return SuccessTip.create(wechatQrCodeService.createPermanent(tenantId, sceneStr));
    }

    @ApiOperation(value = "根据ticket返回二维码地址")
    @GetMapping("/url")
    public Tip getShowQrcodeUrl(@RequestParam String ticket) {
        return SuccessTip.create(wechatQrCodeService.getShowUrl(ticket));
    }
}
