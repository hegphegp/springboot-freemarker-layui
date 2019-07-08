package com.hegp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class HttpSessionController {
    @GetMapping("/test")
    public Map test(HttpServletRequest request) {
        String id = request.getSession(true).getId();
        System.out.println(id);
        Map map = new HashMap();
        map.put("id", id);
        return map;
    }
}
