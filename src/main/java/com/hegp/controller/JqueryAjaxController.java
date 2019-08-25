package com.hegp.controller;

import com.hegp.core.domain.RequestResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/jquery-ajax")
public class JqueryAjaxController {
    @PostMapping(value = "/request-body")
    public RequestResponse testRequestBody(@RequestBody Map params, HttpServletResponse response) {
        Map map = new HashMap();
        map.put("username", params.get("username"));
        map.put("password", params.get("password"));
        System.out.println(map);
        return RequestResponse.build(map);
    }

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
