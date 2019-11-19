package com.wld.modular.test;

import com.wld.common.api.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : LJQ
 * @date : 2019/10/12 0:16
 */
@RestController

public class Test {
    @GetMapping("/1012/test")
    public CommonResult xx() {
        Map map = new HashMap();
        map.put("age", 20);
        map.put("sex", "男");
        Map result = new HashMap();
        result.put("data", map);
        result.put("煞笔", "魏庆萌");
        return CommonResult.success(result);
    }
}
