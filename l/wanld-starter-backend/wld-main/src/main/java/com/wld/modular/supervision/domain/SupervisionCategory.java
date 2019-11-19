package com.wld.modular.supervision.domain;

import com.wld.modular.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "wld_supervision_category")
public class SupervisionCategory extends BaseEntity {
    /**
     * 分类名称
     */
    @Column(name = "title")
    private String title;

    @Column(name = "pid")
    private Long pid;

    @Column(name = "sort")
    private Integer sort;

}
