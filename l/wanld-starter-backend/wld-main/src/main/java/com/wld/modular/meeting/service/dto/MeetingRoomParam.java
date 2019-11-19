package com.wld.modular.meeting.service.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MeetingRoomParam {
    @ApiModelProperty(value = "需要修改信息的id", required = true)
    private Long id;

    @ApiModelProperty(value = "会议室编号", required = true)
    @NotNull(message = "会议室编号不能为空")
    private Integer numberNo;

    @ApiModelProperty(value = "会议室名称", required = true)
    @NotEmpty(message = "会议室名称不能为空")
    private String name;

    @ApiModelProperty(value = "会议室容量")
    @NotNull(message = "会议室容量不能为空")
    private Integer capacity;

}
