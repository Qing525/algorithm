package com.wld.modular.meeting.repository;


import com.wld.modular.meeting.domain.MeetingRecordList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRecordListRepository extends JpaRepository<MeetingRecordList, Long> {
    List<MeetingRecordList> findByMeetingRecordId(Long id);
}
