package com.wld.modular.domain;


import com.wld.modular.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 角色权限
 *
 * @author wangz
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "wld_role_permission_relation")
public class RolePermissionRelation extends BaseEntity {

    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "permission_id", nullable = false)
    private Long permissionId;

}

