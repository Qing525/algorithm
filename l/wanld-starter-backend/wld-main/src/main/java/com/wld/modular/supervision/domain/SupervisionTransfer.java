package com.wld.modular.supervision.domain;

import com.wld.modular.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "wld_supervision_transfer")
public class SupervisionTransfer extends BaseEntity {
    /**
     * 任务转派后的执行人
     */
    @Column(name = "receive_user_id")
    private Long receiveUserId;
    /**
     * 督办人
     */
    @Column(name = "supervise_id")
    private Long superviseId;
    /**
     * 任务转派前的执行人
     */
    @Column(name = "transfer_user_id")
    private Long transferUserId;

}
