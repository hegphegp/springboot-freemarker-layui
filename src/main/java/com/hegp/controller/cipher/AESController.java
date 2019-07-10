package com.hegp.controller.cipher;

import com.hegp.core.domain.RequestResponse;
import com.hegp.core.utiils.Aes;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/crypto")
public class AESController {
    private String sKey = "1234123412ABCDEF";//key，可自行修改，必须是16位。
    private String ivParameter = "ABCDEF1234123412";//偏移量,可自行修改

    @PostMapping(value = "/application-json")
    public RequestResponse applicationJson(@RequestBody LoginParams loginParams) throws Exception {
        if (StringUtils.isEmpty(loginParams.getUsername())||StringUtils.isEmpty(loginParams.getPassword())) {
            throw new RuntimeException("username/password不允许为空");
        }
//        System.out.println(loginParams.getUsername()+"  解密后===>>>  "+ AESUtils.decrypt(loginParams.getUsername(), sKey, ivParameter));
//        System.out.println(loginParams.getPassword()+"  解密后===>>>  "+ AESUtils.decrypt(loginParams.getPassword(), sKey, ivParameter));

        System.out.println(loginParams.getUsername()+"  解密后===>>>  "+ Aes.aesDecrypt(loginParams.getUsername(), sKey));
        System.out.println(loginParams.getPassword()+"  解密后===>>>  "+ Aes.aesDecrypt(loginParams.getPassword(), sKey));
        Map map = new HashMap();
        map.put("username", loginParams.getUsername());
        map.put("password", loginParams.getPassword());
        return RequestResponse.build(map);
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
