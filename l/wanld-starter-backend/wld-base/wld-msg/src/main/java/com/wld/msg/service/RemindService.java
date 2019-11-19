package com.wld.msg.service;

import com.wld.msg.domain.*;
import com.wld.msg.domain.base.RemindActionEnum;
import com.wld.msg.domain.base.RemindObject;
import com.wld.msg.domain.base.RemindUser;

import java.util.List;

/**
 * 消息提醒服务。
 */
public interface RemindService {


    /**
     * 创建消息
     *
     * @param createdUser
     * @param object
     * @param action
     * @return 返回成功创建的消息事件对象
     */
    RemindEvent createRemind(RemindUser createdUser, RemindObject object, RemindActionEnum action);

    List<Remind> getUserRemind(RemindUser userId);

}
