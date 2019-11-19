package com.wld.modular.meeting.service.impl;

import com.wld.config.dozer.MapperUtil;
import com.wld.email.core.model.SendMailParam;
import com.wld.email.provider.MailServiceApi;
import com.wld.modular.domain.User;
import com.wld.modular.meeting.domain.MeetingAppointment;
import com.wld.modular.meeting.domain.MeetingParticipator;
import com.wld.modular.meeting.repository.MeetingAppointmentRepository;
import com.wld.modular.meeting.repository.MeetingParticipatorRepository;
import com.wld.modular.meeting.service.MeetingAppointmentService;
import com.wld.modular.meeting.service.dto.MeetingAppointmentOutput;
import com.wld.modular.meeting.service.dto.MeetingAppointmentParam;
import com.wld.modular.meeting.service.dto.MeetingParticipatorParam;
import com.wld.modular.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Validated
public class MeetingAppointmentServiceImpl implements MeetingAppointmentService {
    @Autowired
    MeetingAppointmentRepository meetingAppointmentRepository;
    @Autowired
    MeetingParticipatorRepository meetingParticipatorRepository;
    @Autowired
    MailServiceApi mailServiceApi;
    @Autowired
    UserRepository userRepository;
    @Override
    public MeetingAppointmentParam register(@Valid MeetingAppointmentParam meetingAppointmentParam) {
        MeetingAppointment meetingAppointment = MapperUtil.map(meetingAppointmentParam, MeetingAppointment.class);
        MeetingAppointment save = meetingAppointmentRepository.save(meetingAppointment);
        return MapperUtil.map(save,MeetingAppointmentParam.class);
    }

    @Override
    public boolean delete(Long id) {
        Optional<MeetingAppointment> deleteMA = meetingAppointmentRepository.findById(id);
        if(!deleteMA.isPresent()){
            return false;
        }
        deleteMA.get().setIsDeleted(true);
        meetingAppointmentRepository.save(deleteMA.get());
        return true;
    }

    @Override
    public MeetingAppointmentParam update(@Valid MeetingAppointmentParam meetingAppointmentParam) {
        Long id=meetingAppointmentParam.getId();
        Optional<MeetingAppointment> meetingAppointment = meetingAppointmentRepository.findById(id);
        if (!meetingAppointment.isPresent()){
            return null;
        }
        MeetingAppointment meetingAppointment1 = MapperUtil.map(meetingAppointmentParam, MeetingAppointment.class);
        meetingAppointment1.setCreatedTime(meetingAppointment.get().getCreatedTime());
        meetingAppointment1.setCreatedUserId(meetingAppointment.get().getCreatedUserId());
        MeetingAppointment save = meetingAppointmentRepository.save(meetingAppointment1);
        return MapperUtil.map(save,MeetingAppointmentParam.class);
    }

    @Override
    public List<MeetingAppointmentOutput> selectAll() {
        return meetingAppointmentRepository.findAllMeetingAppointment();
    }

    @Override
    public MeetingAppointmentOutput selectOne(Long id) {
        Optional<MeetingAppointment> meetingAppointment = meetingAppointmentRepository.findById(id);
        return meetingAppointment.map(appointment -> MapperUtil.map(appointment, MeetingAppointmentOutput.class)).orElse(null);
    }

    @Override
    public boolean register(@Valid MeetingParticipatorParam meetingParticipatorParam) {
        Long meetingAppointmentId = meetingParticipatorParam.getMeetingAppointmentId();
        List<Long> userId = meetingParticipatorParam.getUserId();
        boolean notify = meetingParticipatorParam.isNotify();
        if(userId.size()==0){
            return false;
        }
        if(notify){
            List<User> byIdIn = userRepository.findByIdIn(userId);
            ArrayList<String> list=new ArrayList<>();
            for(User u:byIdIn){
                String email = u.getEmail();
                list.add(email);
            }
            sendEmail(list);
        }
        for(Long uid:userId){
            MeetingParticipator meetingParticipator=new MeetingParticipator();
            meetingParticipator.setUserId(uid);
            meetingParticipator.setMeetingAppointmentId(meetingAppointmentId);
            meetingParticipatorRepository.save(meetingParticipator);
        }
        return true;
    }

    @Override
    public List<MeetingParticipator> allParticipator(Long id) {
        return meetingParticipatorRepository.findByMeetingAppointmentId(id);
    }

    @Override
    public boolean deleteParticipator(Long id) {
        Optional<MeetingParticipator> meetingParticipator = meetingParticipatorRepository.findById(id);
        if(!meetingParticipator.isPresent()){
            return false;
        }
        meetingParticipatorRepository.delete(meetingParticipator.get());
        return true;
    }

    @Override
    public boolean sendEmail(List<String> emails) {
        if(emails.size()==0){
            return false;
        }
        SendMailParam sendMailParam = new SendMailParam();
        String title="您有一个新的会议邀请！";
        String content="会议邀请";
        for (String em:emails){
            sendMailParam.setTo(em);
            sendMailParam.setTitle(title);
            sendMailParam.setContent(content);
            mailServiceApi.sendMail(sendMailParam);
        }
        return true;
    }

}
