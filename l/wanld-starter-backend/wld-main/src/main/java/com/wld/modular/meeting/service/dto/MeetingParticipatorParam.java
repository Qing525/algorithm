package com.wld.modular.meeting.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class MeetingParticipatorParam {

    @ApiModelProperty(value = "预约id", required = true)
    @NotNull(message = "预约id不能为空")
    private Long meetingAppointmentId;

    @ApiModelProperty(value = "user id", required = true)
    @NotNull(message = "user id不能为空")
    private List<Long> userId;

    @ApiModelProperty(value = "notify ", required = true)
    @NotNull(message = "是否通知与会人员不能为空")
    private boolean notify;
}
