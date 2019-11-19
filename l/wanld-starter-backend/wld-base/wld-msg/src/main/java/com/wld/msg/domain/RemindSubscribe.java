package com.wld.msg.domain;


import com.wld.msg.domain.base.AbstractRemindObject;
import com.wld.msg.domain.base.RemindActionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 订阅；  描述谁订阅了哪条资源或哪类型资源的哪种动作的消息。
 *
 * @author 王增光
 * @date 2019/9/16
 */
@Entity
@Table(name = "remind_subscribe")
@Data
@EqualsAndHashCode(callSuper = false)
public class RemindSubscribe extends AbstractRemindObject {

    @Id
    private long id;

    /**
     * 订阅用户
     */
    private long userId;

    /**
     * 订阅的的动作，如：捐款、更新、评论、收藏；
     */
    private RemindActionEnum action;

}
