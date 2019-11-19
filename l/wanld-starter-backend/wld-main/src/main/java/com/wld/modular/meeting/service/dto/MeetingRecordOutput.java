package com.wld.modular.meeting.service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MeetingRecordOutput {
    private Long id;
    private String projectName;
    private String meetingName;
    private String meetingRoomName;
    private Date meetingStartTime;
    private Date meetingEndTime;

    public MeetingRecordOutput(Long id, String projectName, String meetingName, String meetingRoomName, Date meetingStartTime, Date meetingEndTime) {
        this.id = id;
        this.projectName = projectName;
        this.meetingName = meetingName;
        this.meetingRoomName = meetingRoomName;
        this.meetingStartTime = meetingStartTime;
        this.meetingEndTime = meetingEndTime;
    }

    public MeetingRecordOutput() {
    }
}
