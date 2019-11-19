package com.wld.modular.meeting.service.impl;

import com.wld.config.dozer.MapperUtil;
import com.wld.modular.meeting.domain.MeetingRoom;
import com.wld.modular.meeting.repository.MeetingRoomRepository;
import com.wld.modular.meeting.service.MeetingRoomService;
import com.wld.modular.meeting.service.dto.MeetingRoomOutput;
import com.wld.modular.meeting.service.dto.MeetingRoomParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class MeetingRoomServiceImpl implements MeetingRoomService {
    @Autowired
    MeetingRoomRepository meetingRoomRepository;
    @Override
    public MeetingRoomParam register(@Valid MeetingRoomParam meetingRoomParam) {
        MeetingRoom meetingRoom = MapperUtil.map(meetingRoomParam, MeetingRoom.class);
        MeetingRoom save = meetingRoomRepository.save(meetingRoom);
        return MapperUtil.map(save, MeetingRoomParam.class);
    }
    @Override
    public boolean delete(Long id) {
        Optional<MeetingRoom> deleteMR = meetingRoomRepository.findById(id);
        if(!deleteMR.isPresent()){
            return false;
        }
        deleteMR.get().setIsDeleted(true);
        meetingRoomRepository.save(deleteMR.get());
        return true;
    }
    @Override
    public MeetingRoomParam update(@Valid MeetingRoomParam meetingRoomParam) {
        Long id=meetingRoomParam.getId();
        Optional<MeetingRoom> meetingRoom = meetingRoomRepository.findById(id);
        if (!meetingRoom.isPresent()){
            return null;
        }
        MeetingRoom meetingRoom1 = MapperUtil.map(meetingRoomParam, MeetingRoom.class);
        meetingRoom1.setCreatedTime(meetingRoom.get().getCreatedTime());
        meetingRoom1.setCreatedUserId(meetingRoom.get().getCreatedUserId());
        MeetingRoom save = meetingRoomRepository.save(meetingRoom1);
        return MapperUtil.map(save, MeetingRoomParam.class);
    }
    @Override
    public List<MeetingRoomOutput> selectAll() {
        return meetingRoomRepository.findAllMeetingRoom();
    }
    @Override
    public MeetingRoomOutput selectOne(Long id) {
        Optional<MeetingRoom> findOne = meetingRoomRepository.findById(id);
        return findOne.map(meetingRoom -> MapperUtil.map(meetingRoom, MeetingRoomOutput.class)).orElse(null);
    }
}
