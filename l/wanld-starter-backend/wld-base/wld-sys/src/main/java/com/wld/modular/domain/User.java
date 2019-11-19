package com.wld.modular.domain;

import com.wld.modular.core.AbstractSoftDeleted;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author wangz
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "wld_user")
@Where(clause = " is_deleted=0")
public class User extends AbstractSoftDeleted {

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 名字
     */
    private String name;

    /**
     * 姓
     */
    private String surname;

    /**
     * 身份证号，证件号
     */
    private String identityNumber;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String icon;

    /**
     * 备注信息
     */
    private String note;

    /**
     * 最后登录时间
     */
    private Date loginTime;

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

