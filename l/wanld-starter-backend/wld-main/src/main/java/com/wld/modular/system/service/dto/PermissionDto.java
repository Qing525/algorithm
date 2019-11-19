package com.wld.modular.system.service.dto;

import com.wld.modular.core.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionDto extends BaseDto {

    private Long pid;

    private String name;


    private String value;


    private String icon;


    private Integer type;


    private String uri;


    private Integer status;


    private Date createTime;


    private Integer sort;
}
