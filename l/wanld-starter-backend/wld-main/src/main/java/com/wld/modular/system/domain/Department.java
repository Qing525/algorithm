package com.wld.modular.system.domain;

import com.wld.modular.core.AbstractSoftDeleted;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 部门表
 *
 * @author 王增光
 * @date 2019/9/4
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "wld_department")
@Where(clause = " is_deleted=0")
public class Department extends AbstractSoftDeleted {

    /**
     * 上级部门
     */
    @Column(nullable = false)
    @ColumnDefault("0")
    private Long pid = 0L;

    @Column(length = 100, nullable = false)
    private String name;
}
