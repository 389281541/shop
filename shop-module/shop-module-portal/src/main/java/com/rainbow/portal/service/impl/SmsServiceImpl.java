package com.rainbow.portal.service.impl;

import com.rainbow.common.dto.SmsVerifyDTO;
import com.rainbow.portal.constant.SmsContant;
import com.rainbow.portal.service.ISmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SmsServiceImpl implements ISmsService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean sendVerifyCode(String mobile) {
        try {
            MultiValueMap<String, String> param = new LinkedMultiValueMap();
            param.add("sname", SmsContant.SMS_USERNAME);
            param.add("spwd", SmsContant.SMS_PASSWORD);
            //String verifyCode = generateNumericCaptcha();
            //测试环境 为123456
            String verifyCode = "123456";
            redisTemplate.opsForValue().set(SmsContant.PREFIX_SMS + mobile, verifyCode, 1, TimeUnit.MINUTES);
            param.add("smsg", MessageFormat.format(SmsContant.LOGIN_SMS_CONTENT, verifyCode));
            param.add("sprdid", SmsContant.SMS_SPRDID);
            param.add("scorpid", "");
            param.add("sdst", mobile);
            UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(SmsContant.SMS_SEND_URL).queryParams(param).build();
            log.info("发送短信请求：" + uriComponents.toUriString());
            String result = restTemplate.getForObject(uriComponents.toUriString(), String.class, param);
            log.info("发送短信结果：" + result);
        } catch (Exception r) {
            return false;
        }
        return true;
    }


    @Override
    public Boolean verify(SmsVerifyDTO param) {
        //获取缓存保存的验证码
        String exactVerifyCode = redisTemplate.opsForValue().get(SmsContant.PREFIX_SMS + param.getMobile());
        if(Objects.equals(exactVerifyCode, param.getVerifyCode())) {
            return true;
        }
        return false;
    }

    /**
     * 随机生成6位验证码
     * @return
     */
    private String generateNumericCaptcha() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i != 6; ++i) {
            stringBuilder.append(new Random().nextInt(10));
        }
        return stringBuilder.toString();
    }
}
