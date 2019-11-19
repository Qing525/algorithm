package com.wld.modular.core;


import javax.persistence.*;
import java.io.Serializable;


/**
 * @author 王增光
 */
@MappedSuperclass
@EntityListeners(CustomEntityListener.class)
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
