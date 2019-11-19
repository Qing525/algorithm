package com.wld.modular.meeting.service;

import com.wld.modular.meeting.domain.MeetingParticipator;
import com.wld.modular.meeting.service.dto.MeetingAppointmentOutput;
import com.wld.modular.meeting.service.dto.MeetingAppointmentParam;
import com.wld.modular.meeting.service.dto.MeetingParticipatorParam;

import javax.validation.Valid;
import java.util.List;

public interface MeetingAppointmentService {
    /**
     * 增加预约
     */

    MeetingAppointmentParam register(@Valid MeetingAppointmentParam meetingAppointmentParam);
    /**
     * 删除预约
     */
    boolean delete(Long id);
    /**
     * 修改预约信息
     *
     */
    MeetingAppointmentParam update(@Valid MeetingAppointmentParam meetingAppointmentParam);
    /**
     * 查询所有预约
     */
    List<MeetingAppointmentOutput> selectAll();

    /**
     * 根据id查询一个
     */
    MeetingAppointmentOutput selectOne(Long id);

    /**
     * 保存与会人员
     */
    boolean register(@Valid MeetingParticipatorParam meetingParticipatorParam);

    /**
     * 查找全部（会议邀请人员）
     */
    List<MeetingParticipator> allParticipator(Long id);

    /**
     * 删除会议邀请人员
     */
    boolean deleteParticipator(Long id);

    /**
     * 发送邮件
     */
    boolean sendEmail(List<String> emails);
}
