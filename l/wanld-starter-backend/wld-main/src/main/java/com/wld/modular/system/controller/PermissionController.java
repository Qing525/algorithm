package com.wld.modular.system.controller;

import com.wld.common.api.CommonResult;
import com.wld.modular.domain.Permission;
import com.wld.modular.system.service.PermissionService;
import com.wld.modular.system.service.dto.PermissionDto;
import com.wld.modular.system.service.dto.PermissionNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台用户权限管理
 *
 * @author wangzg
 */
@Api(tags = "sys-权限管理")
@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @ApiOperation("添加权限")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody PermissionDto permission) {
        Permission wldPermission = permissionService.create(permission);
        if (null != wldPermission) {
            return CommonResult.success(wldPermission);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改权限")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@RequestBody PermissionDto permission) {
        Permission wldPermission = permissionService.update(permission);
        if (null != wldPermission) {
            return CommonResult.success(wldPermission);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据id批量删除权限")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = permissionService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("以层级结构返回所有权限")
    @RequestMapping(value = "/tree-list", method = RequestMethod.GET)
    public CommonResult<List<PermissionNode>> treeList() {
        List<PermissionNode> permissionNodeList = permissionService.treeList();
        return CommonResult.success(permissionNodeList);
    }

    @ApiOperation("获取所有权限列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<Permission>> list() {
        List<Permission> permissionList = permissionService.list();
        return CommonResult.success(permissionList);
    }
}
