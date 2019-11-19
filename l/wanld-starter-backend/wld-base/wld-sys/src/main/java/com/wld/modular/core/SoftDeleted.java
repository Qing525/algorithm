package com.wld.modular.core;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;


/**
 * @author 王增光
 */

public interface SoftDeleted {

    @Column(name = "is_deleted", nullable = false)
    @ColumnDefault("0")
    Boolean isDeleted = false;

}
