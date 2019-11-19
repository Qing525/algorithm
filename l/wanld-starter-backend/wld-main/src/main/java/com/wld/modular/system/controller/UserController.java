package com.wld.modular.system.controller;

import com.wld.common.api.CommonPage;
import com.wld.common.api.CommonResult;
import com.wld.modular.domain.Permission;
import com.wld.modular.domain.Role;
import com.wld.modular.domain.User;
import com.wld.modular.system.service.UserService;
import com.wld.modular.system.service.dto.Userdto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台用户管理
 *
 * @author wangzg
 */
@Api(tags = "sys-用户管理")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority(T(com.wld.config.PermissionList).USER_GET)")
    public CommonResult<CommonPage<Userdto>> list(@RequestParam(value = "name", required = false) String name,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum) {
        Page<Userdto> userList = userService.list(name, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(userList));
    }

    @ApiOperation("获取指定用户信息")
    @GetMapping(value = "/get/{id}")
    @PreAuthorize("hasAuthority(T(com.wld.config.PermissionList).USER_GET)")
    public CommonResult<Userdto> get(@PathVariable Long id) {
        Userdto user = userService.getItem(id);
        return CommonResult.success(user);
    }

    @ApiOperation("修改指定用户信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority(T(com.wld.config.PermissionList).USER_UPDATE)")
    public CommonResult update(@PathVariable Long id, @RequestBody User user) {
        int count = userService.update(id, user);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除指定用户信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority(T(com.wld.config.PermissionList).USER_DELETE)")
    public CommonResult delete(@PathVariable Long id) {
        int count = userService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("给用户分配角色")
    @RequestMapping(value = "/update-user-role", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority(T(com.wld.config.PermissionList).USER_UPDATE)")
    public CommonResult updateUserRole(@RequestParam("userId") Long userId,
                                       @RequestParam("roleIds") List<Long> roleIds) {
        int count = userService.updateRole(userId, roleIds);
        if (count >= 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取指定用户的角色")
    @RequestMapping(value = "/get-user-role/{userId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority(T(com.wld.config.PermissionList).USER_GET)")
    public CommonResult<List<Role>> getUserRole(@PathVariable Long userId) {
        List<Role> roleList = userService.getRoleList(userId);
        return CommonResult.success(roleList);
    }

    @ApiOperation("给用户分配+-权限")
    @RequestMapping(value = "/update-user-permission", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority(T(com.wld.config.PermissionList).USER_UPDATE)")
    public CommonResult updateUserPermission(@RequestParam Long userId,
                                             @RequestParam("permissionIds") List<Long> permissionIds) {
        int count = userService.updatePermission(userId, permissionIds);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @RequestMapping(value = "/get-user-permission/{userId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority(T(com.wld.config.PermissionList).USER_GET)")
    public CommonResult<List<Permission>> getUserPermission(@PathVariable Long userId) {
        List<Permission> permissionList = userService.getPermissionList(userId);
        return CommonResult.success(permissionList);
    }
}
