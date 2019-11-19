package com.wld.modular.system.repository;

import com.wld.modular.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author LJQ
 **/
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    /**
     * 批量删除权限
     *
     * @param ids
     * @return 删除行数
     */
    @Modifying
    Integer deleteByIdIn(List<Long> ids);

    /**
     * 获取权限列表
     *
     * @param userId
     * @return
     */
    @Query(value = " SELECT  p.*  FROM " +
            "  wld_user_role_relation ar " +
            "  LEFT JOIN wld_role r ON ar.role_id = r.id" +
            "  LEFT JOIN wld_role_permission_relation rp  ON r.id = rp.role_id" +
            "  LEFT JOIN wld_permission p ON rp.permission_id = p.id" +
            "  WHERE ar.user_id = ?1 AND p.id IS NOT NULL  " +
            "  AND p.id NOT IN (" + "  SELECT  p.id  FROM " +
            "  wld_user_permission_relation pr" +
            "  LEFT JOIN wld_permission p ON pr.permission_id = p.id " +
            "  WHERE pr.type = - 1 AND pr.user_id = ?1" + ") UNION " +
            "  SELECT  p.* FROM" +
            "  wld_user_permission_relation pr" +
            "  LEFT JOIN wld_permission p ON pr.permission_id = p.id " +
            "  WHERE  pr.type = 1 AND pr.user_id =?1", nativeQuery = true)
    List<Permission> getPermissionList(Long userId);

    /***
     * 获取角色权限
     * @param userId
     * @return
     */

    @Query(value = "  select p.* " +
            "    from wld_user_role_relation ar left join wld_role r on ar.role_id = r.id" +
            "    left join wld_role_permission_relation rp on r.id = rp.role_id" +
            "    left join wld_permission p on rp.permission_id = p.id" +
            "    where ar.user_id = ? and p.id is not null", nativeQuery = true)
    List<Permission> getRolePermissionList(Long userId);
}
