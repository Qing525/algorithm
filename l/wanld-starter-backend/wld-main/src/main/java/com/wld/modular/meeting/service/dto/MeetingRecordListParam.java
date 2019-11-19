package com.wld.modular.meeting.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MeetingRecordListParam {
    @ApiModelProperty(value = "需要修改信息的id", required = true)
    private Long id;

    @ApiModelProperty(value = "纪要", required = true)
    @NotEmpty(message = "纪要不能为空")
    private String content;

    @ApiModelProperty(value = "记录表id", required = true)
    @NotNull(message = "记录表id不能为空")
    private Long meetingRecordId;

    @ApiModelProperty(value = "议程", required = true)
    @NotEmpty(message = "议程不能为空")
    private String title;
}
