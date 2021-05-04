package com.hegp.controller;

import com.hegp.core.domain.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/v1/jquery-ajax")
public class JqueryAjaxController {
    @RequestMapping(value = "/request-body")
    public Result testRequestBody(@RequestBody Map params, HttpServletResponse response) {
        Map map = new HashMap();
        map.put("username", params.get("username"));
        map.put("password", params.get("password"));
        System.out.println(map);
        return Result.success(map);
    }

    @PostMapping(value = "/x-www-form-urlencoded")
    public Result xWwwFormUrlencoded(HttpServletResponse response,
                                     @RequestParam(name="username", required = false) String username,
                                     @RequestParam(name="password", required = false) String password) {
        Map map = new HashMap();
        map.put("username", username);
        map.put("password", password);
        int random = new Random().nextInt(11);
        if (random/2==0) {
            response.setStatus(401);
            return Result.build(401, "no login");
        } else {
            return Result.success(map);
        }

    }

    @GetMapping(value = "/get")
    public Result get(HttpServletResponse response,
                      @RequestParam(name="username", required = false) String username,
                      @RequestParam(name="password", required = false) String password) {
        Map map = new HashMap();
        map.put("username", username);
        map.put("password", password);
        int random = new Random().nextInt(11);
        if (random/2==0) {
            response.setStatus(401);
            return Result.build(401, "no login");
        } else {
            return Result.success(map);
        }
    }
}
