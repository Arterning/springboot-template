package cn.ning.springboot.starter.controller;


import cn.ning.springboot.starter.service.CacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "缓存")
@Validated
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Resource
    private CacheService cacheService;

    @GetMapping
    @ApiOperation("缓存一下~")
    public ResponseEntity<?> cacheString(@RequestParam  String key, @RequestParam  String value) {
        cacheService.putCache(key, value);
        return ResponseEntity.ok("ok");
    }
}
