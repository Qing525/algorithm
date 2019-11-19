package com.wld.modular.meeting.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class MeetingAppointmentParam {
    @ApiModelProperty(value = "需要修改信息的id", required = true)
    private Long id;

    @ApiModelProperty(value = "会议开始时间", required = true)
    @NotNull(message = "会议开始时间不能为空")
    private Date startTime;

    @ApiModelProperty(value = "会议结束时间", required = true)
    @NotNull(message = "会议结束时间不能为空")
    private Date endTime;

    @ApiModelProperty(value = "会议室id", required = true)
    @NotNull(message = "会议室id不能为空")
    private Long meetingRoomId;
}
