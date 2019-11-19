package com.wld.modular.meeting.repository;

import com.wld.modular.meeting.domain.MeetingRoom;
import com.wld.modular.meeting.service.dto.MeetingRoomOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {
    @Query("select new com.wld.modular.meeting.service.dto.MeetingRoomOutput" +
            "(mr.id,mr.name,mr.numberNo,mr.capacity,mr.createdUserId,mr.createdTime,us.name) from MeetingRoom mr " +
            "left join com.wld.modular.domain.User us on mr.createdUserId=us.id")
    List<MeetingRoomOutput> findAllMeetingRoom();
}
