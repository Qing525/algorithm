package com.wld.modular.supervision.service.dto;

import com.wld.modular.core.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LJQ
 * @date 2019/9/23 10:06
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SupervisionCategoryDto extends BaseDto {

    private Long pid;

    private String title;
}
