package com.hegp.core.utils.encrypt.aes;

import org.springframework.util.Assert;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

/**
 * 可怕的现实,可怕的世界.没任何人解释怎么写,怎么配.全都通过浪费生命(时间)来测试验证
 * 1) java 使用PKCS5Padding填充最后加密的结果也跟Python等语言加密的结果不太一样,原本想放弃这种方式的, 却发现全世界的crypto-js博客都是前端用CryptoJS.pad.Pkcs7, 后端用PKCS5Padding
 * 2) java 不支持PKCS7Padding
 */
/**
 * 众所周知，java填充方式没有PKCS7Padding，而且java使用PKCS5Padding填充最后加密的结果也跟Python等语言加密的结果不太一样，
 * 往往只有前半段相同，后半段不同，像openresty默认的填充方式是PKCS7Padding，那么java的加密结果跟lua的加密结果就更对不上了，
 * 对于两者交互产生了很大的问题。
 */

/**
 * AES/CBC/PKCS5Padding 方式
 */
public class AesCBCUtils {

    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();

    private static final String ALGORITHMSTR = "AES/CBC/PKCS5Padding";


    public static byte[] encryptToBytes(String encData, String secretKey, String iv) throws Exception {
        Assert.isTrue(secretKey!=null&&secretKey.length()>0, "密钥不允许为空");
        Assert.isTrue(secretKey.getBytes("utf-8").length == 16, "密钥.getBytes()后必须是16位");
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        IvParameterSpec ivParameter = new IvParameterSpec(iv.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameter);
        return cipher.doFinal(encData.getBytes("utf-8"));
    }

    // 加密
    public static String encrypt(String encData, String secretKey, String iv) throws Exception {
        return encoder.encodeToString(encryptToBytes(encData, secretKey, iv)); // 此处使用BASE64做转码。
    }

    public static String decryptByBytes(byte[] encrypted, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParameter = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameter);
        byte[] original = cipher.doFinal(encrypted);
        return new String(original, "utf-8");
    }

    public static String decrypt(String sSrc, String key, String iv) throws Exception {
        return decryptByBytes(decoder.decode(sSrc), key, iv);
    }

    public static void main(String[] args) throws Exception {
        // 需要加密的字串
        String cSrc = "[{\"request_no\":\"1001\",\"service_code\":\"FS0001\",\"contract_id\":\"100002\",\"order_id\":\"0\",\"phone_id\":\"13913996922\",\"plat_offer_id\":\"100094\",\"channel_id\":\"1\",\"activity_id\":\"100045\"}]";
        /** 加密用的Key 可以由大小写字母数字组成 此处使用AES-128-CBC加密模式，key需要为16位。 */
        String sKey = "1234123412ABCDEF";//key，可自行修改，必须是16位。
        String ivParameter = "ABCDEF1234123412";//偏移量,可自行修改
        // 加密
        String enString = encrypt(cSrc, sKey, ivParameter);
        System.out.println("加密后的字串是：" + enString);

        // 解密
        String DeString = decrypt(enString, sKey, ivParameter);
        System.out.println("解密后的字串是：" + DeString);
    }

}