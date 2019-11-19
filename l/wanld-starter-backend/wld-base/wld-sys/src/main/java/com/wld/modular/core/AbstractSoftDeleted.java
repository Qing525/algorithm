package com.wld.modular.core;


import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


/**
 * @author 王增光
 * @date 2019/9/5
 */
@MappedSuperclass
public abstract class AbstractSoftDeleted extends AbstractAuditEntity implements SoftDeleted {

    @Column(name = "is_deleted", nullable = false)
    @ColumnDefault("0")
    private Boolean isDeleted = false;

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
