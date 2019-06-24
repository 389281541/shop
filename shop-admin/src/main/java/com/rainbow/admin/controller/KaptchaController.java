package com.rainbow.admin.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.rainbow.admin.api.dto.VerifyCodeDTO;
import com.rainbow.admin.util.CookieUtil;
import com.rainbow.common.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 验证码控制器
 *
 * @author lujinwei
 * @since 2019-06-23
 */
@Api(value = "/kaptcha", position = 2, tags = "验证码服务")
@RestController
@RequestMapping("/kaptcha")
@Slf4j
public class KaptchaController {

    @Autowired
    private DefaultKaptcha captchaProducer;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static String PREFIX_KAPTCHA = "CAPTCHA_";

    private static final String BOM_CAPTCHA_KEY = "BOM_CAPTCHA_KEY";

    @ApiOperation(value = "获取验证码", notes = "获取验证码", httpMethod = "POST")
    @PostMapping("/get")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //产生验证码字符串并保存到redis中
            String createText = captchaProducer.createText();
            String captchaKey = CookieUtil.getCookie(request, BOM_CAPTCHA_KEY);
            if(StringUtils.isBlank(captchaKey)) {
                captchaKey = UUID.randomUUID().toString();
                log.info("New user coming,captcha_key = {}", captchaKey);
                CookieUtil.addCookie(response, BOM_CAPTCHA_KEY, captchaKey);
            }
            redisTemplate.opsForValue().set(PREFIX_KAPTCHA + captchaKey, createText, 1, TimeUnit.MINUTES);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = captchaProducer.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }


    @ApiOperation(value = "校验验证码", notes = "校验验证码", httpMethod = "POST")
    @PostMapping("/verify")
    public R<Map<String, Boolean>> verifyKaptcha(HttpServletRequest request, @RequestBody @Valid VerifyCodeDTO req) {
        //获取验证码Cookie值
        String captchaKey = CookieUtil.getCookie(request, BOM_CAPTCHA_KEY);
        //从缓存中获取验证码
        String cacheVerifyCode = redisTemplate.opsForValue().get(PREFIX_KAPTCHA + captchaKey);
        //删除缓存
        redisTemplate.opsForValue().getOperations().delete(PREFIX_KAPTCHA + captchaKey);
        //比较两处的验证码是否匹配
        Map<String, Boolean> map = Maps.newHashMap();
        map.put("result", Objects.equal(cacheVerifyCode, req.getVrifyCode()));
        return new R<>(map);
    }


}
