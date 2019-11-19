package com.wld.modular.meeting.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class MeetingRecordParam {
    @ApiModelProperty(value = "需要修改信息的id", required = true)
    private Long id;

    @ApiModelProperty(value = "项目名称", required = true)
    @NotEmpty(message = "项目名称不能为空")
    private String projectName;

    @ApiModelProperty(value = "会议名称", required = true)
    @NotEmpty(message = "会议名称不能为空")
    private String meetingName;

    @ApiModelProperty(value = "会议类型")
    private String meetingType;

    @ApiModelProperty(value = "会议主题")
    private String meetingTheme;

    @ApiModelProperty(value = "会议预约表id")
    private Long meetingAppointmentId;

    @ApiModelProperty(value = "会议开始时间")
    private Date meetingStartTime;

    @ApiModelProperty(value = "会议结束时间")
    private Date meetingEndTime;

    @ApiModelProperty(value = "会议室id")
    private Long meetingRoomId;

    @ApiModelProperty(value = "主持人id")
    private Long hostUserId;

    @ApiModelProperty(value = "记录人id")
    private Long recordUserId;

    @ApiModelProperty(value = "督办事项")
    private String supervision;

    @ApiModelProperty(value = "督办激励")
    private String motivation;

    @ApiModelProperty(value = "任务结束时间")
    private Date finishTime;





}
