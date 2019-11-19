package com.wld.modular.domain;


import com.wld.modular.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author wangz
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "wld_user_permission_relation")
public class UserPermissionRelation extends BaseEntity {

    @Column(name = "type")
    private Integer type;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "permission_id", nullable = false)
    private Long permissionId;

}

