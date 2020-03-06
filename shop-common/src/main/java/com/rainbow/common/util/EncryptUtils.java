package com.rainbow.common.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.google.common.io.BaseEncoding;
import com.rainbow.common.dto.token.AuthToken;
import org.apache.commons.lang.StringUtils;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

/**
 * 加密工具类
 * @author lujinwei
 * @date 2019/1/8 上午11:25
 */
public final class EncryptUtils {

    private static final Charset charset = Charset.forName("CP437");

    private static int SALT_LENGTH = 8;

    /**
     * token解码
     *
     * @param token
     * @return
     */
    public static AuthToken strDecode(String token) {
        return JSONObject.parseObject(strDecodeToString(token), AuthToken.class);
    }

    /**
     * token解码成string
     *
     * @param token
     * @return
     */
    public static String strDecodeToString(String token) {
        String src = base64Decode(token);
        int factor = src.charAt(0);
        int c = factor % 8;
        String entity = src.substring(1);
        List<String> slice = Splitter.fixedLength(factor).splitToList(entity);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < slice.size(); i++) {
            byte[] chars = slice.get(i).getBytes(charset);
            for (int j = 0; j < chars.length; j++) {
                byte ch = chars[j];
                int t = ch - c - i;
                while (t < 0) {
                    t += 256;
                }
                sb.append((char) t);
            }
        }

        return sb.toString();
    }


    /**
     * 生成token
     *
     * @param userId
     * @return token AuthToken
     */
    public static String strEncode(Long userId, String userName, String mobile, String openId) {
        // 步骤一：生成三个属性的json，时间精确到秒
        String salt = PasswordUtils.randomStr(SALT_LENGTH);
        AuthToken authToken = new AuthToken();
        authToken.setCt(System.currentTimeMillis());
        authToken.setSalt(salt);
        authToken.setMobile(mobile);
        authToken.setOpenId(openId);
        authToken.setUserId(userId);
        authToken.setUserName(userName);
        String str = JacksonUtils.obj2Str(authToken);
        return base64EncodeStrWithFactor(str);
    }

    /**
     * 提供Token编码服务
     *
     * @author yanghaolei
     * @param data 数据
     * @return 编码后的Token
     */
    public static String base64EncodeStrWithFactor(String data) {
        // 步骤二：生成加密因子 rand(1,min(255, Math.ceil(len%3));
        Random random = new Random();
        int factor = random.nextInt((int) Math.min(255, Math.ceil(data.length() / 3))) + 1;
        int c = factor % 8;
        // 步骤三：字符换值
        List<String> slice = Splitter.fixedLength(factor).splitToList(data);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < slice.size(); i++) {
            byte[] chars = slice.get(i).getBytes(charset);
            for (int j = 0; j < chars.length; j++) {
                byte ch = chars[j];
                byte t = (byte) (ch +  c + i);
                sb.append(new String(new byte[]{t}, charset));
            }
        }
        String token = base64Encode(factor, sb.toString());
        return StringUtils.isNotBlank(token)?token:" ";
    }

    /**
     * 提供Token编码服务 重载类
     */
    public static String base64EncodeStrWithFactor(Object obj) {
        return base64EncodeStrWithFactor(JSONObject.toJSONString(obj));
    }

    private static String base64Encode(int factor, String src) {
        if (StringUtils.isBlank(src)) {
            return "";
        } else {
            src = ((char) factor) + src;
            String deStr = BaseEncoding.base64().encode(src.getBytes(charset));
            String result = deStr.replace('+', '-').replace('/', '_');
            while (result.endsWith("=")) {
                result = result.substring(0, result.length() - 1).toString();
            }
            return result;
        }
    }

    /**
     * 字符串base64 decode
     *
     * @param src
     * @return
     */
    public static String base64Decode(String src) {
        if (StringUtils.isBlank(src)) {
            return "";
        } else {
            String deStr = src.replace('-', '+').replace('_', '/');
            return new String(BaseEncoding.base64().decode(deStr), charset);
        }
    }
}
