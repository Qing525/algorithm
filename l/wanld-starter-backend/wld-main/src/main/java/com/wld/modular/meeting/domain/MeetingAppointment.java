package com.wld.modular.meeting.domain;


import com.wld.modular.core.AbstractSoftDeleted;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "meeting_appointment")
@Where(clause = " is_deleted=0")
public class MeetingAppointment extends AbstractSoftDeleted {
    /**
     * 会议室id
     */
    @Column(name = "meeting_room_id")
    private Long meetingRoomId;
    /**
     * 开始时间
     */
    @Column(name = "start_time")
    private Date startTime;
    /**
     *结束时间
     */
    @Column(name = "end_time")
    private Date endTime;


}
