package com.wld.modular.system.service;


import lombok.Data;

import java.util.List;

@Data
public class RoleInput {

    private Long pid;

    private String name;

    private String value;

    private String description;

    private Integer sort = 0;

    private Boolean isDisabled;

    private List<Long> permissionIds;

}
