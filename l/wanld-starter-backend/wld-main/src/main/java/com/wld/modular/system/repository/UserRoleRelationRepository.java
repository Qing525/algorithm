package com.wld.modular.system.repository;

import com.wld.modular.domain.UserRoleRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LJQ
 * @date 2019/8/28 9:50
 **/
public interface UserRoleRelationRepository extends JpaRepository<UserRoleRelation, Long> {
    /***
     * 根据用户Id 获取用户角色关系
     * @param userId
     * @return
     */
    List<UserRoleRelation> findByUserId(long userId);

}
