package com.wld.modular.domain;

import com.wld.modular.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 用户角色关系表；
 * eager加载
 *
 * @author wangz
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "wld_user_role_relation")
public class UserRoleRelation extends BaseEntity {

    @JoinColumn(name = "user_id", nullable = false)
    private Long userId;

    @JoinColumn(name = "role_id", nullable = false)
    private Long roleId;

}

