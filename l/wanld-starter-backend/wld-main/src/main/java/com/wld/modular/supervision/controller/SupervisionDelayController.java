package com.wld.modular.supervision.controller;

import com.wld.common.api.CommonResult;
import com.wld.config.dozer.MapperUtil;
import com.wld.config.security.SecurityUtil;
import com.wld.modular.supervision.domain.SupervisionDelay;
import com.wld.modular.supervision.service.SupervisionDelayService;
import com.wld.modular.supervision.service.dto.SupervisionDelayDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author LJQ
 * @date 2019/9/25 15:10
 **/
@RestController
@Api(tags = "sup-督办任务延期")
@RequestMapping("/api/supervision/delay")
public class SupervisionDelayController {
    @Autowired
    private SupervisionDelayService delayService;

    @PostMapping("/apply")
    @ApiOperation(value = "执行人申请延期")
    public CommonResult<SupervisionDelayDto> apply(@RequestBody SupervisionDelayDto delayDto) {
        SupervisionDelay delay = delayService.apply(delayDto);
        return CommonResult.success(MapperUtil.map(delay, SupervisionDelayDto.class));
    }

    @GetMapping("/query")
    @ApiOperation(value = "发起人查看延期申请")
    public CommonResult<SupervisionDelayDto> query(@RequestParam Integer status) {
        Long userId = SecurityUtil.getCurrentUserId();
        return CommonResult.success(delayService.findByUserIdAndIsReview(userId, status));
    }

    @GetMapping("/detail")
    @ApiOperation(value = "查看延期申请详情")
    public CommonResult<List<Map<String, Object>>> detail(@RequestParam Long id) {
        return CommonResult.success(delayService.detail(id));
    }

    @PostMapping("/review")
    @ApiOperation(value = "延期申请审核")
    public CommonResult<SupervisionDelayDto> review(@RequestParam Long id, @RequestParam Integer status) {
        SupervisionDelay supervisionDelay = delayService.delayReview(id, status);
        return CommonResult.success(MapperUtil.map(supervisionDelay, SupervisionDelayDto.class));
    }
}
