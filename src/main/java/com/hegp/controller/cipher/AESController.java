package com.hegp.controller.cipher;

import com.hegp.core.domain.RequestResponse;
import com.hegp.core.utiils.encrypt.aes.AesECBUtils;
import com.hegp.core.utiils.encrypt.aes.AesCBCUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/v1/crypto")
public class AESController {
    private String sKey = "1234123412ABCDEF";//key，可自行修改，必须是16位。
    private String ivParameter = "ABCDEF1234123412";//偏移量,可自行修改

    @PostMapping(value = "/aes-cbc-iv")
    public RequestResponse applicationJson(HttpServletResponse response, @RequestBody LoginParams loginParams) throws Exception {
//        response.sendRedirect("https://www.baidu.com");
        if (StringUtils.isEmpty(loginParams.getUsername())||StringUtils.isEmpty(loginParams.getPassword())) {
            throw new RuntimeException("username/password不允许为空");
        }
        System.out.println("AES/CBC/PKCS5Padding方式  ===>>>  "+loginParams.getUsername()+"  解密后===>>>  "+ AesCBCUtils.decrypt(loginParams.getUsername(), sKey, ivParameter));
        System.out.println("AES/CBC/PKCS5Padding方式  ===>>>  "+loginParams.getPassword()+"  解密后===>>>  "+ AesCBCUtils.decrypt(loginParams.getPassword(), sKey, ivParameter));

        return RequestResponse.build(loginParams);
    }

    //  "AES/ECB/PKCS5Padding"
    @PostMapping(value = "/aes-ecb-no-iv")
    public RequestResponse applicationJsonSuccess(@RequestBody LoginParams loginParams) throws Exception {
        if (StringUtils.isEmpty(loginParams.getUsername())||StringUtils.isEmpty(loginParams.getPassword())) {
            throw new RuntimeException("username/password不允许为空");
        }
        System.out.println("AES/ECB/PKCS5Padding方式  ===>>>  "+loginParams.getUsername()+"  解密后===>>>  "+ AesECBUtils.decrypt(loginParams.getUsername(), sKey));
        System.out.println("AES/ECB/PKCS5Padding方式  ===>>>  "+loginParams.getPassword()+"  解密后===>>>  "+ AesECBUtils.decrypt(loginParams.getPassword(), sKey));
        return RequestResponse.build(loginParams);
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
}
