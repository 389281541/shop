package com.rainbow.admin.service.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.common.base.Objects;
import com.rainbow.api.dto.VerifyCodeDTO;
import com.rainbow.api.enums.KaptchaStatusEnum;
import com.rainbow.admin.service.IKaptchaService;
import com.rainbow.common.util.CookieUtils;
import com.rainbow.common.util.UUIDUtils;
import com.rainbow.common.vo.IdNameVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 后台管理用户表 服务实现类
 *
 * @author lujinwei
 * @since 2019-08-25
 */
@Slf4j
@Service
public class KaptchaServiceImpl implements IKaptchaService {

    @Autowired
    private DefaultKaptcha captchaProducer;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static String PREFIX_KAPTCHA = "CAPTCHA_";

    private static final String BOM_CAPTCHA_KEY = "BOM_CAPTCHA_KEY";

    @Override
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //产生验证码字符串并保存到redis中
            String createText = captchaProducer.createText();
            String captchaKey = CookieUtils.getCookieValue(request, BOM_CAPTCHA_KEY);
            if (StringUtils.isBlank(captchaKey)) {
                captchaKey = UUIDUtils.getUpperCaseUUID();
                log.info("New user coming,captcha_key = {}", captchaKey);
                CookieUtils.addCookie(response, BOM_CAPTCHA_KEY, captchaKey);
            }
            redisTemplate.opsForValue().set(PREFIX_KAPTCHA + captchaKey, createText, 1, TimeUnit.MINUTES);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = captchaProducer.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);

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
        } catch (IllegalArgumentException e) {
            log.error("非法参数错误：" + e.getMessage());
        } catch (IOException e) {
            log.error("IO错误：" + e.getMessage());
        } catch (Exception e) {
            log.error("错误：" + e.getMessage());
        }
    }


    @Override
    public IdNameVO verifyKaptcha(HttpServletRequest request, VerifyCodeDTO req) {
        IdNameVO idNameVO = new IdNameVO();
        //获取验证码Cookie值
        String captchaKey = CookieUtils.getCookieValue(request, BOM_CAPTCHA_KEY);
        //从缓存中获取验证码
        String cacheVerifyCode = redisTemplate.opsForValue().get(PREFIX_KAPTCHA + captchaKey);
        //删除缓存
        redisTemplate.opsForValue().getOperations().delete(PREFIX_KAPTCHA + captchaKey);
        //比较两处的验证码是否匹配
        if (StringUtils.isBlank(cacheVerifyCode)) {
            idNameVO.setId(Long.valueOf(KaptchaStatusEnum.EXPIRED.getValue()));
            idNameVO.setName(KaptchaStatusEnum.EXPIRED.getDesc());
        } else {
            if(Objects.equal(cacheVerifyCode,req.getVerifyCode())) {
                idNameVO.setId(Long.valueOf(KaptchaStatusEnum.SUCESS.getValue()));
                idNameVO.setName(KaptchaStatusEnum.SUCESS.getDesc());
            } else {
                idNameVO.setId(Long.valueOf(KaptchaStatusEnum.FAIL.getValue()));
                idNameVO.setName(KaptchaStatusEnum.FAIL.getDesc());
            }
        }
        return idNameVO;
    }
}
