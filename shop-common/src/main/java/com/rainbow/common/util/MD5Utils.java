package com.rainbow.common.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密类
 */
public class MD5Utils {
    private static Logger logger = LoggerFactory.getLogger(MD5Utils.class);

    // 加的盐
    public static final byte WRITE_CASE_MODE_UPPER = 1;
    public static final byte WRITE_CASE_MODE_LOWER = 2;

    public static String encrypByMd5(String context) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(context.getBytes());// update处理
            byte[] encryContext = md.digest();// 调用该方法完成计算

            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < encryContext.length; offset++) {// 做相应的转化（十六进制）
                i = encryContext[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static String EncoderByMd5(String buf) {
        try {
            MessageDigest digist = MessageDigest.getInstance("MD5");
            byte[] rs = digist.digest(buf.getBytes());
            StringBuffer digestHexStr = new StringBuffer();
            for (int i = 0; i < 16; i++) {
                digestHexStr.append(byteHEX(rs[i]));
            }
            return digestHexStr.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }


    /**
     * 加盐的md5值。这样即使被拖库，仍然可以有效抵御彩虹表攻击
     *
     * @param inbuf 需做md5的字符串
     * @param salt  盐
     * @return
     */
    public static String encodeByMd5AndSalt(String inbuf, String salt) {
        return encrypByMd5(encrypByMd5(inbuf) + salt);
    }


    public static String byteHEX(byte ib) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F'};
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }

    public static String mysqlMd5(String plainText, Byte writeCaseMode) {
        if (null == plainText) {
            plainText = "";
        }
        String MD5Str = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuilder builder = new StringBuilder(32);
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    builder.append("0");
                builder.append(Integer.toHexString(i));
            }
            MD5Str = builder.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (writeCaseMode == null || MD5Str == null || MD5Str.trim().length() <= 0) {
            return MD5Str;
        }
        if (writeCaseMode.equals(WRITE_CASE_MODE_UPPER)) {
            return MD5Str.toUpperCase();
        } else if (writeCaseMode.equals(WRITE_CASE_MODE_LOWER)) {
            return MD5Str.toLowerCase();
        }
        return MD5Str;
    }


}
