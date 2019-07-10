package com.hegp.core.utiils;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 可怕的现实,可怕的世界.没任何人解释怎么写,怎么配.全都通过浪费生命(时间)来测试验证
 * 1) java 使用PKCS5Padding填充最后加密的结果也跟Python等语言加密的结果不太一样,原本想放弃这种
 * 2) java 不支持PKCS7Padding
 */
public class AESSimpleUtils {

    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();

    public static String encode(String key, String datas) throws Exception {
        SecretKey skeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(datas.getBytes("UTF-8"));
        return encoder.encodeToString(encrypted);
    }

    public static String decode(String key, String datas) throws Exception {
        SecretKey skeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] encrypted = decoder.decode(datas);
        byte[] decode = cipher.doFinal(encrypted);
        return new String(decode, "utf-8");
    }
 
    public static void main(String[] args) throws Exception {
        String aesKey = "1234123412ABCDEF";//key，可自行修改，必须是16位。
        String password = "这是要加密的内容";
        String encPasswd = AESSimpleUtils.encode(aesKey, password);
        System.out.println("原内容：" + password);
        System.out.println("加密后的内容：" + encPasswd);
        System.out.println("解密后的内容：" + AESSimpleUtils.decode(aesKey, encPasswd));
    }
}