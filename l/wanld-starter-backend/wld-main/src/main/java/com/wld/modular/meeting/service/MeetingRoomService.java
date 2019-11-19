package com.wld.modular.meeting.service;

import com.wld.modular.meeting.service.dto.MeetingRoomOutput;
import com.wld.modular.meeting.service.dto.MeetingRoomParam;

import javax.validation.Valid;
import java.util.List;


public interface MeetingRoomService {
    /**
     * 增加会议室
     */

    MeetingRoomParam register(@Valid MeetingRoomParam meetingRoomParam);
    /**
     * 删除会议室
     */
    boolean delete(Long id);
    /**
     * 修改会议室信息
     *
     */
    MeetingRoomParam update(@Valid MeetingRoomParam meetingRoomParam);

    /**
     * 查询所有
     */
    List<MeetingRoomOutput> selectAll();

    /**
     * 根据id查询一个
     */
    MeetingRoomOutput selectOne(Long id);
}
