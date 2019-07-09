package com.hegp.core.utiils;

import org.springframework.util.Assert;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；
 */
public class AESUtils {

    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();
    private AESUtils() { }

    // 加密
    public static String encrypt(String encData, String secretKey, String vector) throws Exception {
        Assert.isTrue(secretKey!=null&&secretKey.length()>0, "密钥不允许为空");
        Assert.isTrue(secretKey.length() == 16, "密钥必须是16位");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = secretKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(encData.getBytes("utf-8"));
        return encoder.encodeToString(encrypted); // 此处使用BASE64做转码。
    }

    public static String decrypt(String sSrc, String key, String ivs) throws Exception {
        byte[] raw = key.getBytes("ASCII");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] encrypted = decoder.decode(sSrc);
        byte[] original = cipher.doFinal(encrypted);
        return new String(original, "utf-8");
    }

    public static void main(String[] args) throws Exception {
        // 需要加密的字串
        String cSrc = "[{\"request_no\":\"1001\",\"service_code\":\"FS0001\",\"contract_id\":\"100002\",\"order_id\":\"0\",\"phone_id\":\"13913996922\",\"plat_offer_id\":\"100094\",\"channel_id\":\"1\",\"activity_id\":\"100045\"}]";

        /** 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。 */
        String sKey = "smkldospdosldaaa";//key，可自行修改，必须是16位。
        String ivParameter = "0392039203920300";//偏移量,可自行修改
        // 加密
        long lStart = System.currentTimeMillis();
        String enString = encrypt(cSrc, sKey, ivParameter);
        System.out.println("加密后的字串是：" + enString);

        long lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("加密耗时：" + lUseTime + "毫秒");
        // 解密
        lStart = System.currentTimeMillis();
        String DeString = decrypt(enString, sKey, ivParameter);
        System.out.println("解密后的字串是：" + DeString);
        lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("解密耗时：" + lUseTime + "毫秒");
    }

}