package com.wld.modular.system.service.impl;

import com.wld.config.dozer.MapperUtil;
import com.wld.modular.domain.Permission;
import com.wld.modular.domain.Role;
import com.wld.modular.domain.RolePermissionRelation;
import com.wld.modular.system.repository.PermissionRepository;
import com.wld.modular.system.repository.RolePermissionRelationRepository;
import com.wld.modular.system.repository.RoleRepository;
import com.wld.modular.system.service.RoleInput;
import com.wld.modular.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 后台角色管理Service实现类
 *
 * @author wangzg
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RolePermissionRelationRepository rolePermissionRelationRepository;
    @Autowired
    private PermissionRepository permissionRepository;


    @Override
    public Role create(RoleInput role) {
        Role entity = MapperUtil.map(role, Role.class);
        entity = roleRepository.save(entity);
        saveRolePermissions(entity.getId(), role);
        return entity;
    }

    @Override
    public Role update(Long id, RoleInput role) {
        Optional<Role> roleEntity = roleRepository.findById(id);
        if (roleEntity.isPresent()) {
            roleEntity.get().setName(role.getName());
            roleEntity.get().setValue(role.getValue());
            roleEntity.get().setDescription(role.getDescription());
            roleEntity.get().setIsDisabled(role.getIsDisabled());
            Role role1 = roleRepository.save(roleEntity.get());
            List<RolePermissionRelation> rolePermissionRelations = rolePermissionRelationRepository.findByRoleId(id);
            if (!rolePermissionRelations.isEmpty()) {
                rolePermissionRelationRepository.deleteAll(rolePermissionRelations);
            }
            saveRolePermissions(id, role);
            return role1;
        }
        return null;
    }

    private void saveRolePermissions(Long id, RoleInput role) {
        List<RolePermissionRelation> adds = new ArrayList<>();
        for (Long p : role.getPermissionIds()) {
            RolePermissionRelation rolePermissionRelation = new RolePermissionRelation();
            rolePermissionRelation.setRoleId(id);
            rolePermissionRelation.setPermissionId(p);
            adds.add(rolePermissionRelation);
        }
        rolePermissionRelationRepository.saveAll(adds);
    }


    @Override
    public int delete(List<Long> ids) {
        List<Role> roles = roleRepository.findAllById(ids);
        for (Role role : roles) {
            role.setIsDeleted(true);
        }
        roleRepository.saveAll(roles);
        return roles.size();
    }

    @Override
    public List<Permission> getPermissionList(Long roleId) {
        List<RolePermissionRelation> rolePermissionRelationList = rolePermissionRelationRepository.findByRoleId(roleId);
        List<Long> ps = rolePermissionRelationList.stream().map(RolePermissionRelation::getPermissionId).collect(Collectors.toList());
        return permissionRepository.findAllById(ps);
    }

    @Override
    public List<Role> list() {
        return roleRepository.findAll();
    }
}
