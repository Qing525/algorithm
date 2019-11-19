package com.wld.modular.core;

import com.wld.common.util.CreateId;

import javax.persistence.PrePersist;

/**
 * 自定义 EntityListener
 *
 * @author 王增光
 */
public class CustomEntityListener {

    @PrePersist
    public void createId(Object entity) {
        if (entity instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) entity;
            if (baseEntity.getId() == null || baseEntity.getId() == 0L) {
                baseEntity.setId(CreateId.newId());
            }
        }
    }
}
