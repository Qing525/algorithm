package com.wld.modular.system.controller;

import com.wld.common.api.CommonResult;
import com.wld.modular.domain.Permission;
import com.wld.modular.domain.Role;
import com.wld.modular.system.service.RoleInput;
import com.wld.modular.system.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台用户角色管理
 *
 * @author wangzg
 */
@Api(tags = "sys-角色管理")
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation("添加角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority(T(com.wld.config.PermissionList).ROLE_CREATE)")
    public CommonResult create(@RequestBody RoleInput input) {
        Role role = roleService.create(input);
        if (null != role) {
            return CommonResult.success(role);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改角色")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority(T(com.wld.config.PermissionList).ROLE_UPDATE)")
    public CommonResult update(@PathVariable Long id, @RequestBody RoleInput role) {
        Role wldRole = roleService.update(id, role);
        if (null != wldRole) {
            return CommonResult.success(wldRole);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除角色")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority(T(com.wld.config.PermissionList).ROLE_DELETE)")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = roleService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取相应角色权限")
    @RequestMapping(value = "/permission/{roleId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority(T(com.wld.config.PermissionList).ROLE_GET)")
    public CommonResult<List<Permission>> getPermissionList(@PathVariable Long roleId) {
        List<Permission> permissionList = roleService.getPermissionList(roleId);
        return CommonResult.success(permissionList);
    }


    @ApiOperation("获取所有角色")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority(T(com.wld.config.PermissionList).ROLE_GET)")
    public Object list() {
        List<Role> roleList = roleService.list();
        return CommonResult.success(roleList);
    }

}
