package com.hegp.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/crypto")
public class CryptoController {
    @PostMapping(value = "/x-www-form-urlencoded")
    public Map test(@RequestParam(name="username", required = false) String username,
                    @RequestParam(name="password", required = false) String password) {
        Map responeResult = new HashMap();
        responeResult.put("code", 200);
        responeResult.put("msg", "success");
        Map map = new HashMap();
        map.put("username", username);
        map.put("password", password);
        responeResult.put("data", map);
        return responeResult;
    }
}
