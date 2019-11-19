package com.wld.modular.domain;

import com.wld.modular.core.AbstractSoftDeleted;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 权限
 *
 * @author wangz
 */
@Entity
@Table(name = "wld_permission")
@Data
@EqualsAndHashCode(callSuper = false)
@Where(clause = " is_deleted=0")
public class Permission extends AbstractSoftDeleted {

    /**
     * 父级权限id
     */
    private Long pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 权限值
     */
    private String value;

    /**
     * 图标
     */
    private String icon;

    /**
     * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     */
    private Integer type;

    /**
     * 前端资源路径
     */
    private String uri;

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

}

