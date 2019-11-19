package com.wld.msg.domain;

import com.wld.msg.domain.base.AbstractRemindObject;
import com.wld.msg.domain.base.RemindActionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 消息表
 *
 * @author 王增光
 * @date 2019/9/16
 */
@Entity
@Table(name = "remind")
@Data
@EqualsAndHashCode(callSuper = false)
public class Remind extends AbstractRemindObject {

    @Id
    private Long id;

    /**
     * 消息编号
     */
    private String numberNo;

    /**
     * 消息内容，由提醒模版生成，需要提前定义
     */
    private String content;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 消息接收者；可能是对象的所有者或订阅者
     */
    private long recipientId;

    /**
     * 是否阅读
     */
    private String status;

    /**
     * 阅读时间
     */
    private String readTime;

    /**
     * 操作者（消息事件动作触发者）
     */
    private long senderUserId;

    /**
     * 操作者用户名
     */
    private String senderUserName;

    /**
     * 操作者的动作，如：捐款、更新、评论、收藏；
     */
    private RemindActionEnum senderAction;

}
