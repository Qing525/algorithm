package com.wld.modular.meeting.repository;


import com.wld.modular.meeting.domain.MeetingParticipator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingParticipatorRepository extends JpaRepository<MeetingParticipator, Long> {
    List<MeetingParticipator> findByMeetingAppointmentId(Long id);
}
