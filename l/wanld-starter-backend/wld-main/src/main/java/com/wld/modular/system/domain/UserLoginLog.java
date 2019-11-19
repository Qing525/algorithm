package com.wld.modular.system.domain;


import com.wld.modular.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "wld_user_login_log")
public class UserLoginLog extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "ip")
    private String ip;

    @Column(name = "address")
    private String address;

    /**
     * 浏览器登录类型
     */
    @Column(name = "user_agent")
    private String userAgent;

}

