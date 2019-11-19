package com.wld.modular.system.service.impl;

import com.wld.config.dozer.MapperUtil;
import com.wld.modular.domain.Permission;
import com.wld.modular.system.repository.PermissionRepository;
import com.wld.modular.system.service.PermissionService;
import com.wld.modular.system.service.dto.PermissionDto;
import com.wld.modular.system.service.dto.PermissionNode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台用户权限管理Service实现类
 *
 * @author wangzg
 */
@Service
@Validated
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission create(@Valid PermissionDto permissionDto) {
        Permission p = MapperUtil.map(permissionDto, Permission.class);
        return permissionRepository.save(p);
    }

    @Override
    public Permission update(@Valid PermissionDto permission) {
        Permission entity = permissionRepository.findById(permission.getId()).get();

        entity.setSort(permission.getSort());
        entity.setIcon(permission.getIcon());
        entity.setPid(permission.getPid());
        entity.setType(permission.getType());
        entity.setUri(permission.getUri());
        entity.setValue(permission.getValue());
        entity.setName(permission.getName());

        return permissionRepository.save(entity);
    }

    @Override
    public int delete(List<Long> ids) {
        return permissionRepository.deleteByIdIn(ids);
    }

    @Override
    public List<PermissionNode> treeList() {
        List<Permission> permissionList = permissionRepository.findAll();
        List<PermissionNode> result = permissionList.stream()
                .filter(permission -> permission.getPid().equals(0L))
                .map(permission -> covert(permission, permissionList)).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Permission> list() {
        return permissionRepository.findAll();
    }

    /**
     * 将权限转换为带有子级的权限对象
     * 当找不到子级权限的时候map操作不会再递归调用covert
     */
    private PermissionNode covert(Permission permission, List<Permission> permissionList) {
        PermissionNode node = new PermissionNode();
        BeanUtils.copyProperties(permission, node);
        List<PermissionNode> children = permissionList.stream()
                .filter(subPermission -> subPermission.getPid().equals(permission.getId()))
                .map(subPermission -> covert(subPermission, permissionList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
