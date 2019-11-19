package com.wld.modular.meeting.repository;


import com.wld.modular.meeting.domain.MeetingAppointment;
import com.wld.modular.meeting.service.dto.MeetingAppointmentOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeetingAppointmentRepository extends JpaRepository<MeetingAppointment, Long> {

    @Query("select new com.wld.modular.meeting.service.dto.MeetingAppointmentOutput" +
            "(ma.id,ma.meetingRoomId,ma.startTime,ma.endTime,mr.name,us.name) from MeetingAppointment ma left join " +
            "MeetingRoom mr on ma.meetingRoomId=mr.id left join User us on ma.createdUserId=us.id")
    List<MeetingAppointmentOutput> findAllMeetingAppointment();

}
