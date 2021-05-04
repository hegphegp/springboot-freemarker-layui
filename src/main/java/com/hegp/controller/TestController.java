package com.hegp.controller;

import com.hegp.core.domain.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class TestController {

    @GetMapping(value = "/get")
    public Result<String> testGet(@RequestParam(name="fields", required = false) List<String> fields,
                                  @RequestParam(name="field01", required = false) String field01,
                                  @RequestParam(name="field02", required = false) String field02,
                                  @RequestParam(name="field03", required = false) String field03) {
        Map map = new HashMap();
        map.put("fields", fields);
        map.put("field01", field01);
        map.put("field02", field02);
        map.put("field03", field03);
        return Result.success(map);
    }

    @PostMapping(value = "/post01")
    public Result<String> testPost01(@RequestParam(name="field01", required = false) String field01,
                                     @RequestParam(name="field02", required = false) String field02,
                                     @RequestParam(name="field03", required = false) String field03,
                                     @RequestBody Map map) {
        map.put("field01", field01);
        map.put("field02", field02);
        map.put("field03", field03);
        return Result.success(map);
    }

    @PostMapping(value = "/post02")
    public Result<String> testPost02(@RequestParam(name="field01", required = false) String field01,
                                     @RequestParam(name="field02", required = false) String field02,
                                     @RequestParam(name="field03", required = false) String field03) {
        Map map = new HashMap();
        map.put("field01", field01);
        map.put("field02", field02);
        map.put("field03", field03);
        return Result.success(map);
    }

    @PutMapping(value = "/put01/{id}")
    public Result<String> testPut01(@PathVariable(name="id", required = false) String id,
                                    @RequestParam(name="field01", required = false) String field01,
                                    @RequestParam(name="field02", required = false) String field02,
                                    @RequestParam(name="field03", required = false) String field03) {
        Map map = new HashMap();
        map.put("id", id);
        map.put("field01", field01);
        map.put("field02", field02);
        map.put("field03", field03);
        return Result.success(map);
    }

    @PutMapping(value = "/put02/{id}")
    public Result<String> testPut02(@PathVariable(name="id", required = false) String id,
                                    @RequestParam(name="field01", required = false) String field01,
                                    @RequestParam(name="field02", required = false) String field02,
                                    @RequestParam(name="field03", required = false) String field03,
                                    @RequestBody Map map) {
        map.put("id", id);
        map.put("field01", field01);
        map.put("field02", field02);
        map.put("field03", field03);
        return Result.success(map);
    }

    @PutMapping(value = "/delete01/{id}")
    public Result<String> testDelete01(@RequestParam(name="id", required = false) String id,
                                       @RequestParam(name="field01", required = false) String field01,
                                       @RequestParam(name="field02", required = false) String field02,
                                       @RequestParam(name="field03", required = false) String field03,
                                       @RequestBody Map map) {
        map.put("id", id);
        map.put("field01", field01);
        map.put("field02", field02);
        map.put("field03", field03);
        return Result.success(map);
    }

}
