package com.wld.modular.meeting.domain;

import com.wld.modular.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "meeting_participator")
public class MeetingParticipator extends BaseEntity {
    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;
    /**
     * 预约表id
     */
    @Column(name = "meeting_appointment_id")
    private Long meetingAppointmentId;
}
