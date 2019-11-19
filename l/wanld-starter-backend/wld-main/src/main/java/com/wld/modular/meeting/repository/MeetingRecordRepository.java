package com.wld.modular.meeting.repository;


import com.wld.modular.meeting.domain.MeetingRecord;
import com.wld.modular.meeting.service.dto.MeetingRecordOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeetingRecordRepository extends JpaRepository<MeetingRecord, Long> {
    @Query("select new com.wld.modular.meeting.service.dto.MeetingRecordOutput" +
            "(mrd.id,mrd.projectName,mrd.meetingName,mr.name,mrd.meetingStartTime,mrd.meetingEndTime) from " +
            "MeetingRecord mrd left join MeetingRoom mr on mrd.meetingRoomId=mr.id")
    List<MeetingRecordOutput> findAllMeetingRecord();

}
