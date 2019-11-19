package com.wld.modular.domain;

import com.wld.modular.core.AbstractSoftDeleted;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色表
 *
 * @author wangz
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "wld_role")
@Where(clause = " is_deleted=0")
public class Role extends AbstractSoftDeleted {

    /**
     * 父角色
     */
    private Long pid;

    /**
     * 名称
     */
    private String name;

    private String value;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    @Column(name = "sort", nullable = false)
    private Integer sort = 0;

    /**
     * 启用状态
     */
    @Column(name = "is_disabled", nullable = false)
    private Boolean isDisabled = false;

    /**
     * 内置的不能删除修改；
     */
    @Column(name = "is_static", nullable = false)
    private Boolean isStatic = false;

}

