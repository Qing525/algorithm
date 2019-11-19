package com.wld.modular.meeting.domain;

import com.wld.modular.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "meeting_record_list")
@Where(clause = " is_deleted=0")
public class MeetingRecordList extends BaseEntity {
    /**
     *议程
     */
    @Column(name = "title")
    private String title;
    /**
     *纪要
     */
    @Column(name = "content")
    private String content;
    /**
     *记录表id
     */
    @Column(name = "meeting_record_id")
    private Long meetingRecordId;
    /**
     *软删除
     */
    @Column(name = "is_deleted", nullable = false)
    @ColumnDefault("0")
    private Boolean isDeleted = false;
}
