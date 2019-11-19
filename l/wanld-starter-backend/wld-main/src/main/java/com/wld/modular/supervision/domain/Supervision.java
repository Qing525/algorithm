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
@Table(name = "wld_supervision")
public class Supervision extends BaseEntity {
    /**
     * 任务编号
     */
    @Column(name = "number_no")
    private String numberNo;

    /**
     * 任务类型 0定期任务 1周期任务
     */
    @Column(name = "type")
    private Integer type;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;
    /**
     * 0一级任务 1二级任务
     */
    @Column(name = "level")
    private Integer level;
    /**
     * 发起人
     */
    @Column(name = "initiator_user_id")
    private Long initiatorUserId;
    /**
     * 发起时间
     */
    @Column(name = "initiate_time")
    private Date initiateTime;
    /**
     * 督办人
     */
    @Column(name = "supervise_user_id")
    private Long superviseUserId;
    /**
     * 督办时间(发起人审核通过的时间)
     */
    @Column(name = "supervise_time")
    private Date superviseTime;
    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "commit_time")
    private Date commitTime;
    /**
     * 分配任务撤回时间
     */
    @Column(name = "retract_time")
    private Date retractTime;
    /**
     * 自定义分类
     */
    @Column(name = "supervision_category_id")
    private Long supervisionCategoryId;
    /**
     * 审核状态   0 未审核 1通过   2驳回
     */
    @Column(name = "is_review")
    private Integer isReview;

    @Column(name = "pid")
    private Long pid;
    /**
     * 执行人
     */
    @Column(name = "receive_user_id")
    private Long receiveUserId;
    /**
     * 绩效
     */
    @Column(name = "performance")
    private String performance;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 接收状态 0未接收   1接收     2录入(新建任务后的状态)
     */
    @Column(name = "is_receive")
    private Integer isReceive;
    /**
     * 完成状态   0未完成   1完成    2逾期完成
     */
    @Column(name = "is_complete")
    private Integer isComplete;
}
