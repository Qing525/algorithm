package com.wld.modular.meeting.service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MeetingAppointmentOutput {
    private Long id;
    private Long meetingRoomId;
    private Date startTime;
    private Date endTime;
    private String meetingRoomName;
    private String userName;

    public MeetingAppointmentOutput(Long id, Long meetingRoomId, Date startTime, Date endTime, String meetingRoomName, String userName) {
        this.id = id;
        this.meetingRoomId = meetingRoomId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetingRoomName = meetingRoomName;
        this.userName = userName;
    }

    public MeetingAppointmentOutput() {
    }
}
