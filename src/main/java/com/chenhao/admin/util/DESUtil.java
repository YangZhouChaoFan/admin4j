package com.chenhao.admin.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.log4j.Logger;

/**
 * DES对传输报文信息进行加密，加密的key采用RSA算法生成的
 *
 * @author daohui
 */
public class DESUtil {
    private static Logger log = Logger.getLogger(DESUtil.class);

    /**
     * @param datasource
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] datasource, String key) {
        SecureRandom random = new SecureRandom();
        try {
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            // 现在，获取数据并加密
            // 正式执行加密操作 O
            return cipher.doFinal(datasource);
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException", e);
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
        } catch (InvalidKeySpecException e) {
            log.error("InvalidKeySpecException", e);
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException", e);
        } catch (IllegalBlockSizeException e) {
            log.error("IllegalBlockSizeException", e);
        } catch (BadPaddingException e) {
            log.error("BadPaddingException", e);
        }
        return null;
    }

    /**
     * @param src
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String key) {
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            // 真正开始解密操作
            return cipher.doFinal(src);
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException", e);
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
        } catch (InvalidKeySpecException e) {
            log.error("InvalidKeySpecException", e);
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException", e);
        } catch (IllegalBlockSizeException e) {
            log.error("IllegalBlockSizeException", e);
        } catch (BadPaddingException e) {
            log.error("BadPaddingException", e);
        }
        return null;
    }

}
