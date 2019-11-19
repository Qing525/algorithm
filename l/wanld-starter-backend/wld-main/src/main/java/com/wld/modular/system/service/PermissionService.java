package com.wld.modular.system.service;

import com.wld.modular.domain.Permission;
import com.wld.modular.system.service.dto.PermissionDto;
import com.wld.modular.system.service.dto.PermissionNode;

import javax.validation.Valid;
import java.util.List;

/**
 * 后台用户权限管理Service
 *
 * @author wangzg
 */
public interface PermissionService {
    /**
     * 添加权限
     */
    Permission create(@Valid PermissionDto permission);

    /**
     * 修改权限
     */
    Permission update(@Valid PermissionDto permission);

    /**
     * 批量删除权限
     */
    int delete(List<Long> ids);

    /**
     * 以层级结构返回所有权限
     */
    List<PermissionNode> treeList();

    /**
     * 获取所有权限
     */
    List<Permission> list();
}
