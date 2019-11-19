package com.wld.modular.meeting.repository;


import com.wld.modular.meeting.domain.MeetingRecordParticipator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRecordParticipatorRepository extends JpaRepository<MeetingRecordParticipator, Long> {
    List<MeetingRecordParticipator> findByMeetingRecordId(Long id);
}
