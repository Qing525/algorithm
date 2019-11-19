package com.wld.modular.supervision.controller;

import com.wld.common.api.CommonResult;
import com.wld.config.dozer.MapperUtil;
import com.wld.config.security.SecurityUtil;
import com.wld.modular.supervision.domain.Supervision;
import com.wld.modular.supervision.domain.SupervisionTransfer;
import com.wld.modular.supervision.service.SupervisionService;
import com.wld.modular.supervision.service.dto.SupervisionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author LJQ
 * @date 2019/9/11 15:08
 **/

@RestController
@Api(tags = "sup-督办任务")
@RequestMapping("/api/supervision")
public class SupervisionController {
    @Autowired
    private SupervisionService supervisionService;

    @GetMapping("/initiate")
    @ApiOperation(value = "查看由自己发起的任务")
    public CommonResult<List<SupervisionDto>> initiate(@RequestParam Integer status) {
        Long userId = SecurityUtil.getCurrentUserId();
        return CommonResult.success(supervisionService.findByInitiatorUserIdAndIsReviewEquals(userId, status));
    }

    @GetMapping("/supervise")
    @ApiOperation(value = "查看由自己督办的任务")
    public CommonResult<List<SupervisionDto>> supervise(@RequestParam Integer status) {
        Long userId = SecurityUtil.getCurrentUserId();
        return CommonResult.success(supervisionService.findBySuperviseUserId(userId, status));
    }

    @GetMapping("/receive")
    @ApiOperation(value = "查看分派给自己的任务")
    public CommonResult<List<SupervisionDto>> receive(@RequestParam(required = false) Integer isReceive, @RequestParam(required = false) Integer isComplete) {
        List<Supervision> supervisions = supervisionService.queryReceive(isReceive, isComplete);
        return CommonResult.success(supervisions.size() > 0 ? MapperUtil.map(supervisions, SupervisionDto.class) : null);
    }

    @PostMapping("/create")
    @ApiOperation(value = "新建督办任务")
    public CommonResult<SupervisionDto> create(@RequestBody SupervisionDto supervisionDTO) {
        Supervision supervision = supervisionService.create(supervisionDTO);
        return CommonResult.success(MapperUtil.map(supervision, SupervisionDto.class));
    }

    @PostMapping("/review")
    @ApiOperation(value = "督办任务审核")
    public CommonResult<SupervisionDto> review(@RequestParam Long supervisionId, @RequestParam Integer status) {
        Supervision supervision = supervisionService.review(supervisionId, status);
        return CommonResult.success(MapperUtil.map(supervision, SupervisionDto.class));

    }

    @PostMapping("/retract")
    @ApiOperation(value = "督办任务撤回")
    public CommonResult retract(@RequestParam Long supervisionId) {
        Supervision supervision = supervisionService.retract(supervisionId);
        return CommonResult.success(MapperUtil.map(supervision, SupervisionDto.class));
    }

    @PostMapping("/assign")
    @ApiOperation(value = "督办任务分派")
    public CommonResult assign(@RequestParam Long supervisionId, @RequestParam Long receiveId) {
        Supervision supervision = supervisionService.assign(supervisionId, receiveId);
        return CommonResult.success(MapperUtil.map(supervision, SupervisionDto.class));
    }

    @PostMapping("/transfer")
    @ApiOperation(value = "督办任务转派")
    public CommonResult<SupervisionTransfer> transfer(@RequestParam Long supervisionId, @RequestParam Long receiveId) {
        //必须先接收才能转发
        return CommonResult.success(supervisionService.transfer(supervisionId, receiveId));
    }

    @GetMapping("/query-child")
    @ApiOperation(value = "查看二级任务的子任务")
    public CommonResult<List<SupervisionDto>> queryChild(@RequestParam Long supervisionId) {
        List<Supervision> supervisionList = supervisionService.findByPid(supervisionId);
        return CommonResult.success(MapperUtil.map(supervisionList, SupervisionDto.class));
    }

    @GetMapping("/detail")
    @ApiOperation(value = "查看督办任务详情")
    public CommonResult<List<Map<String, Object>>> detail(@RequestParam Long supervisionId) {
        return CommonResult.success(supervisionService.querySupervisionDetail(supervisionId));
    }

    @PostMapping("/accept")
    @ApiOperation(value = "接收督办任务")
    public CommonResult<SupervisionDto> accept(@RequestParam Long supervisionId) {
        Supervision supervision = supervisionService.receiveSupervision(supervisionId);
        return CommonResult.success(MapperUtil.map(supervision, SupervisionDto.class));
    }

    @PostMapping("/complete")
    @ApiOperation(value = "督办任务完成")
    public CommonResult<SupervisionDto> complete(@RequestParam Long supervisionId) {
        Supervision supervision = supervisionService.complete(supervisionId);
        return CommonResult.success(MapperUtil.map(supervision, SupervisionDto.class));
    }
}
