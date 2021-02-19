package com.hegp.controller.cipher;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.hegp.core.domain.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * java的PKCS5Padding 对应的 CryptoJS 是 CryptoJS.pad.Pkcs7
 */
@RestController
@RequestMapping("/v1/crypto")
public class AESController {
    private String sKey = "1234123412ABCDEF";//key，可自行修改，必须是16位。
    private String ivParameter = "ABCDEF1234123412";//偏移量,可自行修改
    private AES aesIV = new AES(Mode.CBC, Padding.PKCS5Padding, sKey.getBytes(), ivParameter.getBytes());
    private AES aesNoIV = new AES(Mode.CBC, Padding.PKCS5Padding, sKey.getBytes());

    @PostMapping(value = "/aes-cbc-iv")
    public Result applicationJson(HttpServletResponse response, @RequestBody LoginParams loginParams) {
//        response.sendRedirect("https://www.baidu.com");
        if (StringUtils.isEmpty(loginParams.getUsername())||StringUtils.isEmpty(loginParams.getPassword())) {
            throw new RuntimeException("username/password不允许为空");
        }
        System.out.println("AES/CBC/PKCS5Padding方式  ===>>>  "+loginParams.getUsername()+"  解密后===>>>  "+ aesIV.decryptStr(loginParams.getUsername()));
        System.out.println("AES/CBC/PKCS5Padding方式  ===>>>  "+loginParams.getPassword()+"  解密后===>>>  "+ aesIV.decryptStr(loginParams.getPassword()));

        return Result.build(loginParams);
    }

    //  "AES/ECB/PKCS5Padding"
    @PostMapping(value = "/aes-cbc-no-iv")
    public Result applicationJsonSuccess(@RequestBody LoginParams loginParams) {
        if (StringUtils.isEmpty(loginParams.getUsername())||StringUtils.isEmpty(loginParams.getPassword())) {
            throw new RuntimeException("username/password不允许为空");
        }
        System.out.println("AES/ECB/PKCS5Padding方式  ===>>>  "+loginParams.getUsername()+"  解密后===>>>  "+ aesNoIV.decryptStr(loginParams.getUsername()));
        System.out.println("AES/ECB/PKCS5Padding方式  ===>>>  "+loginParams.getPassword()+"  解密后===>>>  "+ aesNoIV.decryptStr(loginParams.getPassword()));
        return Result.build(loginParams);
    }

    public static class LoginParams {
        private String username;
        private String password;
        public LoginParams() { }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


    public static void main(String[] args) throws Exception {
        // 需要加密的字串
        String cSrc = "[{\"request_no\":\"1001\",\"service_code\":\"FS0001\",\"contract_id\":\"100002\",\"order_id\":\"0\",\"phone_id\":\"13913996922\",\"plat_offer_id\":\"100094\",\"channel_id\":\"1\",\"activity_id\":\"100045\"}]";
        String sKey = "1234123412ABCDEF1234123412ABCDEF";//key，可自行修改，必须是16位或者32位，不要作死填17位，18位长度，出错后，我没时间救自己
        String ivParameter = "1234123412ABCDEF";//偏移量,可自行修改，必须是16位, 不要作死填17位，18位长度，出错后，我没时间救自己

        // 不要作死，不要纠结一定要用 Padding.PKCS7Padding，能力有限，只用现有的功能，hutool暂时没提供 Padding.PKCS7Padding
        // 网上的人说AES比DES更难破解，加密速度更快，更省内存，那么直接忽略DES，只用AES
        // 不存在 AES/NONE/PKCS5Padding，用了就直接扑街死人了
        // Mode.ECB模式不能带 ivParameter 参数，带了就直接扑街死人了

        /**
         *   NONE  无模式
         *   CBC   密码分组连接模式
         *   CFB   密文反馈模式
         *   CTR   计数器模式
         *   CTS   Cipher Text Stealing
         *   ECB   电子密码本模式
         *   OFB   输出反馈模式
         *   PCBC  Propagating Cipher Block
         */
        // new AES(Mode.CBC, Padding.PKCS5Padding, byte[] key, byte[] iv) 等同于 "AES/CBC/PKCS5Padding";
        // new AES(Mode.CFB, Padding.PKCS5Padding, byte[] key, byte[] iv) 等同于 "AES/CFB/PKCS5Padding";
        // new AES(Mode.CTR, Padding.PKCS5Padding, byte[] key, byte[] iv) 等同于 "AES/CTR/PKCS5Padding";
        // new AES(Mode.CTS, Padding.PKCS5Padding, byte[] key, byte[] iv) 等同于 "AES/CTS/PKCS5Padding";
        // new AES(Mode.OFB, Padding.PKCS5Padding, byte[] key, byte[] iv) 等同于 "AES/OFB/PKCS5Padding";
        // new AES(Mode.PCBC, Padding.PKCS5Padding, byte[] key, byte[] iv) 等同于 "AES/PCBC/PKCS5Padding";
        // AES aes = new AES(Mode.CFB, Padding.PKCS5Padding, sKey.getBytes(), ivParameter.getBytes());
        AES aes = new AES(Mode.PCBC, Padding.PKCS5Padding, sKey.getBytes(), ivParameter.getBytes());
        String encryptStr = aes.encryptBase64(cSrc);
        System.out.println(encryptStr);
        System.out.println(aes.decryptStr(encryptStr));
    }
}
