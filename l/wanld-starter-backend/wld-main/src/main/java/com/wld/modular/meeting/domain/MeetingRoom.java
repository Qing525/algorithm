package com.wld.modular.meeting.domain;

import com.wld.modular.core.AbstractSoftDeleted;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "meeting_room")
@Where(clause = " is_deleted=0")
public class MeetingRoom extends AbstractSoftDeleted {
    /**
     *会议室编号
     */
    @Column(name = "number_no")
    private Integer numberNo;
    /**
     *会议室名称
     */
    @Column(name = "name")
    private String name;
    /**
     *会议室容量
     */
    @Column(name = "capacity")
    private Integer capacity;
}
