package com.rainbow.common.util;

import org.apache.commons.lang.StringUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * 密码处理工具，兼容PHP的hash_pbkdf2
 *
 * @author lujinwei
 * @since 2019-01-08
 */
public class PasswordUtils {
    private static final String PREFIX = "sha256";
    private static final String SPLIT = "$";

    private static final int SALT_BYTE_SIZE = 24;

    private static final int ITERATION_INDEX = 1;
    private static final int SALT_INDEX = 2;
    private static final int PBKDF2_INDEX = 3;

    private static final String DEF_ALGORITHM = "HmacSHA256";
    private static final int DEF_ITERATIONS = 12000;
    private static final int DEF_PWD_LEN = 64;

    private static final char[] SALT_POOL =
            {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C',
                    'V', 'B', 'N', 'M', 'z', 'x', 'c', 'v', 'b', 'n', 'm', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'q',
                    'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '7', '8', '9', '4', '5', '6', '1', '2', '3', '0'};

    public static final char[] PWD_LETTER_POOL = {'z', 'x', 'c', 'v', 'b', 'n', 'm', 'a', 's', 'd', 'f', 'h', 'j', 'k',
            'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'p'};

    public static final char[] PWD_NUMBER_POOL = {'6', '3', '2', '5', '8', '7', '4'};

    /**
     * 默认的密码加密算法
     *
     * @param password 明文密码
     * @return 加密后的密码
     */
    public static String createHash(String password) {
        return createHash(password, DEF_ALGORITHM, DEF_ITERATIONS, DEF_PWD_LEN);
    }

    /**
     * 密码加密 123456使用HmacSHA256算法，24字节的盐，迭代12000次的加密样例如下
     * sha256$12000$JybgbOcMcwgF$2184ac0ffe9e4d6a75b4480313ebd3b574acc59c66843116fa9f3f866b6ca60b53188d1c1399db26ae5bd4dd1050d19cbc04ae1326f584d4ff7668b2b5920960
     *
     * @param password   明文密码
     * @param algorithm  加密算法，Message Authentication Code(Mac)
     * @param iterations 迭代次数
     * @param pwdLen     密码长度
     * @return 加密后的密码
     */
    public static String createHash(String password, String algorithm, int iterations, int pwdLen) {
        byte[] passwordHash = password.getBytes();
        String saltStr = randomStr(SALT_BYTE_SIZE / 2);
        byte[] salt = saltStr.getBytes();
        byte[] hash = PBKDF2(passwordHash, salt, iterations, pwdLen, algorithm);
        return StringUtils.join(new String[]{PREFIX, String.valueOf(iterations), saltStr, toHex(hash)}, SPLIT);
    }

    /**
     * 校验密码
     *
     * @param password    明文密码
     * @param correctHash 加密后的密码
     * @return 校验结果
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static boolean validatePassword(String password, String correctHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        return validatePassword(password.getBytes(), correctHash);
    }

    /**
     * 校验密码
     *
     * @param password    明文密码
     * @param correctHash 加密后的密码
     * @return 校验结果
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static boolean validatePassword(byte[] password, String correctHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] params = StringUtils.split(correctHash, SPLIT);
        int iterations = Integer.parseInt(params[ITERATION_INDEX]);
        byte[] salt = params[SALT_INDEX].getBytes();
        byte[] hash = fromHex(params[PBKDF2_INDEX]);
        byte[] testHash = PBKDF2(password, salt, iterations, hash.length, DEF_ALGORITHM);
        return slowEquals(hash, testHash);
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method is used so that password hashes cannot
     * be extracted from an on-line system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

    /**
     * Converts a string of hexadecimal characters into a byte array.
     *
     * @param hex the hex string
     * @return the hex string decoded into a byte array
     */
    public static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    /**
     * Converts a byte array into a hexadecimal string.
     *
     * @param array the byte array to convert
     * @return a length*2 character string encoding the byte array
     */
    public static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    /**
     * 生成随机字符串
     *
     * @param len 字符串长度
     * @return 随机字符串
     */
    public static String randomStr(int len) {
        return randomStr(len, SALT_POOL);
    }

    /**
     * 生成随机字符串
     *
     * @param len      字符串长度
     * @param charPool 字符池
     * @return
     */
    public static String randomStr(int len, final char[] charPool) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[len];
        random.nextBytes(salt);
        char[] saltChars = new char[len];
        int poolSize = charPool.length;
        for (int i = 0; i < len; i++) {
            saltChars[i] = charPool[(salt[i] + 128) % poolSize];
        }
        return new String(saltChars);
    }

    /**
     * 生成随机密码字符串
     *
     * @param len 字符串长度
     * @return 随机密码字符串
     */
    public static String randomPwd(int len) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[len];
        random.nextBytes(salt);
        char[] saltChars = new char[len];
        int letterPoolSize = PWD_LETTER_POOL.length;
        int numberPoolSize = PWD_NUMBER_POOL.length;
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                saltChars[i] = PWD_LETTER_POOL[(salt[i] + 128) % letterPoolSize];
            } else {
                saltChars[i] = PWD_NUMBER_POOL[(salt[i] + 128) % numberPoolSize];
            }
        }
        return new String(saltChars);
    }

    /**
     * Tests the basic functionality of the PasswordUtils class
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        try {

            System.out.println(createHash("abc123"));

            System.out.println(" ==== " + createHash("111111mm"));

            System.out.println(createHash("shizi11@123"));
            System.out.println(createHash("shizi02@123"));
            System.out.println(createHash("shizi03@123"));
            // Print out 10 hashes
            for (int i = 0; i < 10; i++) {
                System.out.println(createHash("p\r\nassw0Rd!"));
            }

            // Test for php
            boolean failure = false;
            System.out.println("Running tests...");
            long timestamp = System.currentTimeMillis();
            String phpPassword = "111111mm";
            String correctHash =
                    "sha256$12000$avCGHdALlxyW$4c4efdd4d4fe056a08469a6f2ea7af3eec01c4d23a5be1fc5f623beff96ad0ca02657be2e9cfbd9826b06907ef2be4467ecf1a4239699b49be6a2105f8cdaa28";
            if (validatePassword(phpPassword, correctHash) == false) {
                System.out.println("FAILURE: VALIDATE PHP PASSWORD FAIL!");
                failure = true;
            } else {
                System.out.println(" VALIDATE PHP PASSWORD SUCCESSFULLY!");
            }

            // Test password validation
            for (int i = 0; i < 100; i++) {
                String password = "" + i;
                String hash = createHash(password);
                String secondHash = createHash(password);
                if (hash.equals(secondHash)) {
                    System.out.println("FAILURE: TWO HASHES ARE EQUAL!");
                    failure = true;
                }
                String wrongPassword = "" + (i + 1);
                if (validatePassword(wrongPassword, hash)) {
                    System.out.println("FAILURE: WRONG PASSWORD ACCEPTED!");
                    failure = true;
                }
                if (!validatePassword(password, hash)) {
                    System.out.println("FAILURE: GOOD PASSWORD NOT ACCEPTED!");
                    failure = true;
                }
            }
            timestamp = System.currentTimeMillis() - timestamp;
            if (failure) {
                System.out.println("TESTS FAILED[" + timestamp + "]!");
            } else {
                System.out.println("TESTS PASSED[" + timestamp + "]!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * PBKDF2算法实现，具体算法参考RFC2898
     *
     * @param pwd       密码
     * @param salt      盐
     * @param count     迭代次数
     * @param dkLen     密钥长度
     * @param algorithm 散列算法
     * @return 加密后的字节数组
     */
    public static byte[] PBKDF2(byte[] pwd, byte[] salt, int count, int dkLen, String algorithm) {
        Mac mac = null;
        try {
            mac = createMac(pwd, algorithm);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[dkLen];
        }
        int hLen = 32;
        int l = dkLen / hLen;
        if (dkLen % hLen != 0) {
            l++;
        }
        byte[] allT = new byte[hLen * l];
        byte[] t = null;
        byte[] u = null;
        for (int i = 1; i <= l; i++) {
            t = new byte[hLen];
            u = arraycat(salt, intToByteArray(i));
            for (int j = 1; j <= count; j++) {
                u = encrypt(u, mac);
                t = xor(t, u);
            }
            System.arraycopy(t, 0, allT, (i - 1) * hLen, hLen);
        }
        byte[] result = new byte[dkLen];
        System.arraycopy(allT, 0, result, 0, dkLen);
        return result;
    }

    /**
     * 创建Mac加密对象
     *
     * @param key     密钥
     * @param encName Mac加密算法
     * @return Mac加密对象
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static Mac createMac(byte[] key, String encName) throws NoSuchAlgorithmException, InvalidKeyException {
        if (encName == null || encName.equals("")) {
            encName = DEF_ALGORITHM;
        }
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, encName);
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化mac
        mac.init(secretKey);
        return mac;
    }

    /**
     * 根据给定的加密对象加密数据
     *
     * @param data 待加密数据
     * @param mac  Mac加密对象
     * @return 加密后的数据
     */
    private static byte[] encrypt(byte[] data, Mac mac) {
        try {
            // 执行消息摘要
            return mac.doFinal(data);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 大端模式转换整型为字节数组
     *
     * @param integer
     * @return
     */
    private static byte[] intToByteArray(final int integer) {
        int byteNum = (40 - Integer.numberOfLeadingZeros(integer < 0 ? ~integer : integer)) / 8;
        byte[] byteArray = new byte[4];

        for (int n = 0; n < byteNum; n++) {
            byteArray[3 - n] = (byte) (integer >>> (n * 8));
        }

        return (byteArray);
    }

    /**
     * 连接字节数组
     *
     * @param buf1
     * @param buf2
     * @return 连接后的字节数组
     */
    private static byte[] arraycat(byte[] buf1, byte[] buf2) {
        byte[] bufret = null;
        int len1 = 0;
        int len2 = 0;
        if (buf1 != null) {
            len1 = buf1.length;
        }
        if (buf2 != null) {
            len2 = buf2.length;
        }
        if (len1 + len2 > 0) {
            bufret = new byte[len1 + len2];
        }
        if (len1 > 0) {
            System.arraycopy(buf1, 0, bufret, 0, len1);
        }
        if (len2 > 0) {
            System.arraycopy(buf2, 0, bufret, len1, len2);
        }
        return bufret;
    }

    /**
     * 字节数组异或操作，对相对短的字节数组末尾补0
     *
     * @param buf1 字节数组1
     * @param buf2 字节数组2
     * @return 异或后的字节数组
     */
    private static byte[] xor(byte[] buf1, byte[] buf2) {
        int len1 = 0;
        int len2 = 0;
        if (buf1 == null) {
            buf1 = new byte[0];
        } else {
            len1 = buf1.length;
        }
        if (buf2 == null) {
            buf2 = new byte[0];
        } else {
            len2 = buf2.length;
        }
        int len = 0;
        if (len1 == len2) {
            len = len1;
        } else if (len1 > len2) {
            len = len1;
            byte[] temp = new byte[len1];
            System.arraycopy(buf2, 0, temp, 0, len2);
            buf2 = temp;
        } else {
            len = len2;
            byte[] temp = new byte[len2];
            System.arraycopy(buf1, 0, temp, 0, len1);
            buf1 = temp;
        }
        if (len == 0) {
            return new byte[0];
        }
        byte[] bufret = null;
        bufret = new byte[len];
        for (int i = 0; i < len; i++) {
            bufret[i] = (byte) (buf1[i] ^ buf2[i]);
        }
        return bufret;
    }

}
