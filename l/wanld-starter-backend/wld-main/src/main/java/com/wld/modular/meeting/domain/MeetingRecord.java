package com.wld.modular.meeting.domain;


import com.wld.modular.core.AbstractAuditEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "meeting_record")
@Where(clause = " is_deleted=0")
public class MeetingRecord extends AbstractAuditEntity {
    /**
     * 项目名称
     */
    @Column(name = "project_name")
    private String projectName;
    /**
     *会议名称
     */
    @Column(name = "meeting_name")
    private String meetingName;
    /**
     * 会议类型
     */
    @Column(name = "meeting_type")
    private String meetingType;
    /**
     *会议主题
     */
    @Column(name = "meeting_theme")
    private String meetingTheme;
    /**
     *会议室id
     */
    @Column(name = "meeting_room_id")
    private Long meetingRoomId;
    /**
     *主持人id
     */
    @Column(name = "host_user_id")
    private Long hostUserId;
    /**
     *记录人id
     */
    @Column(name = "recorder_user_id")
    private Long recorderUserId;
    /**
     * 会议开始时间
     */
    @Column(name = "meeting_start_time")
    private Date meetingStartTime;
    /**
     *会议结束时间
     */
    @Column(name = "meeting_end_time")
    private Date meetingEndTime;
    /**
     * 督办项目
     */
    @Column(name = "supervision")
    private String supervision;
    /**
     *任务完成时间
     */
    @Column(name = "finish_time")
    private Date finishTime;
    /**
     * 督办激励
     */
    @Column(name = "motivation")
    private String motivation;
    /**
     *预约表id
     */
    @Column(name = "meeting_appointment_id")
    private Long meetingAppointmentId;
    /**
     *软删除
     */
    @Column(name = "is_deleted", nullable = false)
    @ColumnDefault("0")
    private Boolean isDeleted = false;
}
