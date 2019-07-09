package com.hegp.controller;

import com.hegp.core.domain.RequestResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/jquery-ajax")
public class JqueryAjaxController {
    @PostMapping(value = "/x-www-form-urlencoded")
    public RequestResponse xWwwFormUrlencoded(@RequestParam(name="username", required = false) String username,
                                              @RequestParam(name="password", required = false) String password) {
        Map map = new HashMap();
        map.put("username", username);
        map.put("password", password);
        return RequestResponse.build(map);
    }

    @GetMapping(value = "/get")
    public RequestResponse get(@RequestParam(name="username", required = false) String username,
                               @RequestParam(name="password", required = false) String password) {
        Map map = new HashMap();
        map.put("username", username);
        map.put("password", password);
        return RequestResponse.build(map);
    }
}
