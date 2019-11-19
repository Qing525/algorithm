package com.wld.modular.supervision.service.dto;

import com.wld.modular.core.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author LJQ
 * @date 2019/9/25 14:16
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SupervisionDelayDto extends BaseDto {

    private Long supervisionId;

    private String cause;

    private Date endTime;

    private String title;

}
