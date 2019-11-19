package com.wld.modular.system.service;

import com.wld.modular.domain.Permission;
import com.wld.modular.domain.Role;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

/**
 * 后台角色管理Service
 *
 * @author wangzg
 */
public interface RoleService {
    /**
     * 添加角色
     */
    Role create(RoleInput role);

    /**
     * 修改角色信息
     */
    Role update(Long id, RoleInput role);

    /**
     * 批量删除角色
     */
    @Modifying
    int delete(List<Long> ids);

    /**
     * 获取指定角色权限
     *
     * @param roleId
     * @return
     */
    List<Permission> getPermissionList(Long roleId);

    /**
     * 获取角色列表
     */
    List<Role> list();
}
