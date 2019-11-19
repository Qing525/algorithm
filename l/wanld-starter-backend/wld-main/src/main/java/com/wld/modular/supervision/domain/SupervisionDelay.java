package com.wld.modular.supervision.domain;

import com.wld.modular.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "wld_supervision_delay")
public class SupervisionDelay extends BaseEntity {

    @Column(name = "supervision_id")
    private Long supervisionId;
    /**
     * 申请原因
     */
    @Column(name = "cause")
    private String cause;
    /**
     * 申请状态 0未审核  1通过  2驳回
     */
    @Column(name = "is_review")
    private Integer isReview;
    /**
     * 延期后的结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "title")
    private String title;
}
