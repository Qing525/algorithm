package com.wld.modular.meeting.service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MeetingRoomOutput {
    private Long id;
    private String name;
    private int numberNo;
    private int capacity;
    private Long createUserId;
    private Date createTime;
    private String userName;

    public MeetingRoomOutput(Long id, String name, int numberNo, int capacity, Long createUserId, Date createTime, String userName) {
        this.id = id;
        this.name = name;
        this.numberNo = numberNo;
        this.capacity = capacity;
        this.createUserId = createUserId;
        this.createTime = createTime;
        this.userName = userName;
    }

    public MeetingRoomOutput() {
    }
}
