package com.wld.modular.supervision.service.dto;

import com.wld.modular.core.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author LJQ
 * @date 2019/9/16 17:31
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SupervisionDto extends BaseDto {

    private Long pid;

    private Integer type;

    private String title;

    private String content;

    private Integer level;

    private Date initiateTime;

    private Long initiatorUserId;

    private String performance;

    private String remark;

    private Integer isReceive;

    private Date endTime;

    private Integer isComplete;

    private Long supervisionCategoryId;

}
