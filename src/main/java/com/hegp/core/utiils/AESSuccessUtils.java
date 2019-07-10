package com.hegp.core.utiils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES的加密和解密
 */
public class AESSuccessUtils {
    private static Base64.Encoder encoder = java.util.Base64.getEncoder();
    private static Base64.Decoder decoder = java.util.Base64.getDecoder();

    //算法
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
      
    /** 
     * AES加密 
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥 
     * @return 加密后的byte[] 
     * @throws Exception 
     */  
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {  
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));  
        return cipher.doFinal(content.getBytes("utf-8"));
    }  
  
      /**
     * AES加密为base 64 code 
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥 
     * @return 加密后的base 64 code 
     * @throws Exception 
     */  
    public static String aesEncrypt(String content, String encryptKey) throws Exception {  
        return encoder.encodeToString(aesEncryptToBytes(content, encryptKey));
    }  
  
    /** 
     * AES解密 
     * @param encryptBytes 待解密的byte[] 
     * @param decryptKey 解密密钥 
     * @return 解密后的String 
     * @throws Exception 
     */  
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {  
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));  
        byte[] decryptBytes = cipher.doFinal(encryptBytes);  
        return new String(decryptBytes);  
    }  
  
  
    /** 
     * 将base 64 code AES解密 
     * @param encryptStr 待解密的base 64 code 
     * @param decryptKey 解密密钥 
     * @return 解密后的string 
     * @throws Exception 
     */  
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {  
        return aesDecryptByBytes(decoder.decode(encryptStr), decryptKey);
    }
    
    /**
     * 测试
     */
    public static void main(String[] args) throws Exception {
        String KEY = "1234123412ABCDEF";//key，可自行修改，必须是16位。
        String content = "123";  
        System.out.println("加密前：" + content);  
        System.out.println("加密密钥和解密密钥：" + KEY);  
        String encrypt = aesEncrypt(content, KEY);  
        System.out.println("加密后：" + encrypt);  
        String decrypt = aesDecrypt(encrypt, KEY);  
        System.out.println("解密后：" + decrypt);  
    } 
}