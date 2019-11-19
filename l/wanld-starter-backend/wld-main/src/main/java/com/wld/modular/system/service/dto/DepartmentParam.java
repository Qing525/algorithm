package com.wld.modular.system.service.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class DepartmentParam {

    private Long id;

    private Long pid;

    @Size(max = 15, message = "名字最长为15")
    private String name;

    private Date createTime;
}
