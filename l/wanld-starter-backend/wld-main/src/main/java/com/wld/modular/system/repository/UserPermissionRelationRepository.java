package com.wld.modular.system.repository;

import com.wld.modular.domain.UserPermissionRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LJQ
 * @date 2019/8/28 9:49
 **/
public interface UserPermissionRelationRepository extends JpaRepository<UserPermissionRelation, Long> {

    /**
     * 根据userId 找出 查询  用户权限关系
     * 一个用户可能对应多个权限
     *
     * @param userId
     * @return
     */
    List<UserPermissionRelation> findWldUserPermissionRelationsByUserId(Long userId);

}
