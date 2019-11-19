package com.wld.modular.system.service.dto;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class Userdto {

    private long id;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String username;

    private String name;

    private String surname;

    private String identityNumber;

    private String nickname;

    private String icon;

    private String note;

    private Date loginTime;

    private Integer status;

    private  Date createdTime;

}
