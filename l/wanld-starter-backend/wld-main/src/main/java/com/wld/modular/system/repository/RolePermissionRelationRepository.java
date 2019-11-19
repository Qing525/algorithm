package com.wld.modular.system.repository;


import com.wld.modular.domain.RolePermissionRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @author LJQ
 **/
public interface RolePermissionRelationRepository extends JpaRepository<RolePermissionRelation, Long> {

    /**
     * 根据角色Id获取 角色权限关系
     *
     * @param roleId
     * @return
     */
    List<RolePermissionRelation> findByRoleId(Long roleId);
}
