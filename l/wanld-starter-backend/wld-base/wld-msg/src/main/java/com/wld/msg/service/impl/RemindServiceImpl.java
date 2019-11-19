package com.wld.msg.service.impl;

import com.wld.msg.domain.Remind;
import com.wld.msg.domain.RemindEvent;
import com.wld.msg.domain.base.RemindActionEnum;
import com.wld.msg.domain.base.RemindObject;
import com.wld.msg.domain.base.RemindUser;
import com.wld.msg.repository.RemindEventRepository;
import com.wld.msg.repository.RemindRepository;
import com.wld.msg.service.RemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemindServiceImpl implements RemindService {

    @Autowired
    private RemindEventRepository eventRepository;

    @Autowired
    private RemindRepository remindRepository;

    @Override
    public RemindEvent createRemind(RemindUser createdUser, RemindObject object, RemindActionEnum action) {

        RemindEvent event = new RemindEvent();

        event.setUserId(createdUser.getId());
        event.setUserName(createdUser.getUsername());
        event.setAction(action);
        event.setObject(object.getObject());
        event.setObjectId(object.getObjectId());
        event.setObjectContent(object.getObjectContent());
        event.setObjectType(object.getObjectType());
        eventRepository.save(event);

        createRemind();

        return event;
    }

    /**
     * 根据提醒事件，产生用户提醒。
     */
    private void createRemind() {

    }


    @Override
    public List<Remind> getUserRemind(RemindUser remindUser) {
        String strLong = Long.toString(remindUser.getId());

        List<Remind> reminds = remindRepository.findAll();

        return new ArrayList<>();
    }
}
