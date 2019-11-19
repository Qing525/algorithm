package com.wld.modular.system.domain;


import com.wld.modular.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 部门主管表
 *
 * @author 王增光
 * @date 2019/9/4
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "wld_department_manager")
public class DepartmentManager extends BaseEntity {

    @Column(name = "department_id", nullable = false)
    private Long departmentId;

    /**
     * 部门主管 用户id
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

}
