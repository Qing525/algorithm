package com.wld.modular.meeting.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class MeetingRecordParticipatorParam {
    @ApiModelProperty(value = "记录表id", required = true)
    @NotNull(message = "预约id不能为空")
    private Long meetingRecordId;

    @ApiModelProperty(value = "user id", required = true)
    @NotNull(message = "user id不能为空")
    private List<Long> userId;
}
