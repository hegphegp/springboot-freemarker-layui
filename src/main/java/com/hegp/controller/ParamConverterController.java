package com.hegp.controller;

import com.hegp.core.domain.Result;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/param-converter")
public class ParamConverterController {

    @GetMapping(value = "/test-long-date")
    public Result testLongDate(@RequestParam(name="date", required = false) Date date) {
        Map map = new HashMap();
        map.put("date", date);
        System.out.println(map);
        return Result.build(map);
    }

    @GetMapping(value = "/test-str-date")
    public Result testStrDate() {
        if (1==1) throw new RuntimeException("1111111");
        Date date = new Date();
        Map map = new HashMap();
        map.put("date", date);
        return Result.build(map);
    }

    @GetMapping(value = "/test-default-value-now-date")
    public Result testDefaultValueNowDate(@RequestParam(name="date", defaultValue = "now") Date date) {
        Map map = new HashMap();
        map.put("date", date);
        return Result.build(map);
    }

}
