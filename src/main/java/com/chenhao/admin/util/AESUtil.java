package com.chenhao.admin.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

/**
 * AES算法解密
 *
 * @author daohui
 */
public class AESUtil {
    private static Logger log = Logger.getLogger(AESUtil.class);

    /**
     * 加密
     *
     * @param content
     * @param password
     * @return
     */
    public static byte[] encrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES"); // KeyGenerator提供（对称）密钥生成器的功能。使用getInstance													// 类方法构造密钥生成器。
            kgen.init(128, new SecureRandom(password.getBytes()));// 使用用户提供的随机源初始化此密钥生成器，使其具有确定的密钥大小。
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 使用SecretKeySpec类来根据一个字节数组构造一个
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器 //为创建 Cipher。
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content); // 按单部分操作加密或解密数据，或者结束一个多部分操作。数据将被加密或解密（具体取决于此
            return result;
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException:" + e);
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException:" + e);
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException:" + e);
        } catch (IllegalBlockSizeException e) {
            log.error("IllegalBlockSizeException:" + e);
        } catch (BadPaddingException e) {
            log.error("BadPaddingException:" + e);
        }
        return null;
    }

    /**
     * 解密算法
     *
     * @param content
     * @param password
     * @return
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException:" + e);
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException:" + e);
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException:" + e);
        } catch (IllegalBlockSizeException e) {
            log.error("IllegalBlockSizeException:" + e);
        } catch (BadPaddingException e) {
            log.error("BadPaddingException:" + e);
        }
        return null;
    }


}
