package com.rainbow.common.util;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * BeanUtils工具类
 *
 * @author lujinwei
 * @since 2020/4/23
 */
public class GeneratorUtils {

    public static String generateParentOrderNO(Long customerId) {
        StringBuilder sb = new StringBuilder();
        String datetime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        sb.append(datetime);
        sb.append(String.format("%06d", customerId));
        return sb.toString();
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString()
                .replaceAll("-", "")
                .toLowerCase();

    }

}
