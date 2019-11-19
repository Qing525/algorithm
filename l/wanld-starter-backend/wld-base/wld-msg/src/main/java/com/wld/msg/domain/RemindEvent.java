package com.wld.msg.domain;


import com.wld.msg.domain.base.AbstractRemindObject;
import com.wld.msg.domain.base.RemindActionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 消息表事件; 产生消息的事件。
 *
 * @author 王增光
 * @date 2019/9/16
 */
@Entity
@Table(name = "remind_event")
@Data
@EqualsAndHashCode(callSuper = false)
public class RemindEvent extends AbstractRemindObject {

    @Id
    private long id;

    /**
     * 操作者（消息事件动作触发者）
     */
    private long userId;

    /**
     * 操作者用户名
     */
    private String userName;

    /**
     * 操作者的动作，如：捐款、更新、评论、收藏；
     */
    private RemindActionEnum action;

}
