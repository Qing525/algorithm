package com.wld.modular.meeting.domain;

import com.wld.modular.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "meeting_record_participator")
public class MeetingRecordParticipator extends BaseEntity {
    /**
     *用户id
     */
    @Column(name = "user_id")
    private Long userId;
    /**
     *记录表id
     */
    @Column(name = "meeting_record_id")
    private Long meetingRecordId;
}
