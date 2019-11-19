package com.wld.modular.meeting.service.impl;

import com.wld.config.dozer.MapperUtil;
import com.wld.modular.meeting.domain.MeetingRecord;
import com.wld.modular.meeting.domain.MeetingRecordList;
import com.wld.modular.meeting.domain.MeetingRecordParticipator;
import com.wld.modular.meeting.repository.MeetingRecordListRepository;
import com.wld.modular.meeting.repository.MeetingRecordParticipatorRepository;
import com.wld.modular.meeting.repository.MeetingRecordRepository;
import com.wld.modular.meeting.service.MeetingRecordService;
import com.wld.modular.meeting.service.dto.MeetingRecordListParam;
import com.wld.modular.meeting.service.dto.MeetingRecordOutput;
import com.wld.modular.meeting.service.dto.MeetingRecordParam;
import com.wld.modular.meeting.service.dto.MeetingRecordParticipatorParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class MeetingRecordServiceImpl implements MeetingRecordService {
    @Autowired
    private MeetingRecordRepository meetingRecordRepository;
    @Autowired
    private MeetingRecordParticipatorRepository meetingRecordParticipatorRepository;
    @Autowired
    private MeetingRecordListRepository meetingRecordListRepository;

    @Override
    public MeetingRecordParam register(@Valid MeetingRecordParam meetingRecordParam) {
        MeetingRecord meetingRecord = MapperUtil.map(meetingRecordParam, MeetingRecord.class);
        MeetingRecord save = meetingRecordRepository.save(meetingRecord);
        return MapperUtil.map(save, MeetingRecordParam.class);
    }

    @Override
    public boolean delete(Long id) {
        Optional<MeetingRecord> deleteMR = meetingRecordRepository.findById(id);
        if(!deleteMR.isPresent()){
            return false;
        }
        deleteMR.get().setIsDeleted(true);
        List<MeetingRecordList> recordLists = meetingRecordListRepository.findByMeetingRecordId(id);
        for (MeetingRecordList meetingRecordList:recordLists){
            meetingRecordList.setIsDeleted(true);
            meetingRecordListRepository.save(meetingRecordList);
        }
        meetingRecordRepository.save(deleteMR.get());
        return true;
    }

    @Override
    public MeetingRecordParam update(@Valid MeetingRecordParam meetingRecordParam) {
        Long id=meetingRecordParam.getId();
        Optional<MeetingRecord> meetingRecord = meetingRecordRepository.findById(id);
        if(!meetingRecord.isPresent()){
            return null;
        }
        MeetingRecord meetingRecord1 = MapperUtil.map(meetingRecordParam, MeetingRecord.class);
        meetingRecord1.setCreatedTime(meetingRecord.get().getCreatedTime());
        meetingRecord1.setCreatedUserId(meetingRecord.get().getCreatedUserId());
        MeetingRecord save = meetingRecordRepository.save(meetingRecord1);
        return MapperUtil.map(save, MeetingRecordParam.class);
    }

    @Override
    public List<MeetingRecordOutput> selectAll() {
        return meetingRecordRepository.findAllMeetingRecord();
    }

    @Override
    public MeetingRecordOutput selectOne(Long id) {
        Optional<MeetingRecord> one = meetingRecordRepository.findById(id);
        return one.map(meetingRecord -> MapperUtil.map(meetingRecord, MeetingRecordOutput.class)).orElse(null);
    }

    @Override
    public boolean register(@Valid MeetingRecordParticipatorParam meetingRecordParticipatorParam) {
        List<Long> userId = meetingRecordParticipatorParam.getUserId();
        Long meetingRecordId = meetingRecordParticipatorParam.getMeetingRecordId();
        if (userId.size()==0){
            return false;
        }
        for (Long uid:userId){
            MeetingRecordParticipator meetingRecordParticipator = new MeetingRecordParticipator();
            meetingRecordParticipator.setUserId(uid);
            meetingRecordParticipator.setMeetingRecordId(meetingRecordId);
            meetingRecordParticipatorRepository.save(meetingRecordParticipator);
        }
        return true;
    }

    @Override
    public List<MeetingRecordParticipator> allRecordParticipator(Long id) {
        return meetingRecordParticipatorRepository.findByMeetingRecordId(id);
    }

    @Override
    public boolean deleteRecordParticipator(Long id) {
        MeetingRecordParticipator meetingRecordParticipator = meetingRecordParticipatorRepository.getOne(id);
        if (meetingRecordParticipator==null){
            return false;
        }
        meetingRecordParticipatorRepository.delete(meetingRecordParticipator);
        return true;
    }

    @Override
    public MeetingRecordListParam register(@Valid MeetingRecordListParam meetingRecordListParam) {
        MeetingRecordList meetingRecordList = MapperUtil.map(meetingRecordListParam, MeetingRecordList.class);
        MeetingRecordList save = meetingRecordListRepository.save(meetingRecordList);
        return MapperUtil.map(save, MeetingRecordListParam.class);
    }

    @Override
    public MeetingRecordListParam updateRecordList(@Valid MeetingRecordListParam meetingRecordListParam) {
        Long id = meetingRecordListParam.getId();
        Optional<MeetingRecordList> meetingRecordList = meetingRecordListRepository.findById(id);
        if (!meetingRecordList.isPresent()){
            return null;
        }
        MeetingRecordList meetingRecordList1 = MapperUtil.map(meetingRecordListParam, MeetingRecordList.class);
        meetingRecordList1.setIsDeleted(meetingRecordList.get().getIsDeleted());
        MeetingRecordList save = meetingRecordListRepository.save(meetingRecordList1);
        return MapperUtil.map(save, MeetingRecordListParam.class);
    }

    @Override
    public boolean deleteRecordList(Long id) {
        Optional<MeetingRecordList> meetingRecordList = meetingRecordListRepository.findById(id);
        if (!meetingRecordList.isPresent()){
            return false;
        }
        meetingRecordList.get().setIsDeleted(true);
        meetingRecordListRepository.save(meetingRecordList.get());
        return true;
    }

    @Override
    public MeetingRecordListParam findOneList(Long id) {
        Optional<MeetingRecordList> meetingRecordList = meetingRecordListRepository.findById(id);
        return meetingRecordList.map(recordList -> MapperUtil.map(recordList, MeetingRecordListParam.class)).orElse(null);
    }

    @Override
    public List<MeetingRecordListParam> findAllList(Long meetingRecordId) {
        List<MeetingRecordList> recordLists = meetingRecordListRepository.findByMeetingRecordId(meetingRecordId);
        ArrayList<MeetingRecordListParam> meetingRecordListParams = new ArrayList<>();
        for (MeetingRecordList mrl:recordLists){
            MeetingRecordListParam map = MapperUtil.map(mrl, MeetingRecordListParam.class);
            meetingRecordListParams.add(map);
        }
        return meetingRecordListParams;
    }
}
