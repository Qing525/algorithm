package com.wld.modular.meeting.service;

import com.wld.modular.meeting.domain.MeetingRecordParticipator;
import com.wld.modular.meeting.service.dto.MeetingRecordListParam;
import com.wld.modular.meeting.service.dto.MeetingRecordOutput;
import com.wld.modular.meeting.service.dto.MeetingRecordParam;
import com.wld.modular.meeting.service.dto.MeetingRecordParticipatorParam;

import javax.validation.Valid;
import java.util.List;

public interface MeetingRecordService {
    /**
     * 增加记录
     */

    MeetingRecordParam register(@Valid MeetingRecordParam meetingRecordParam);
    /**
     * 删除记录
     */
    boolean delete(Long id);
    /**
     * 修改记录信息
     *
     */
    MeetingRecordParam update(@Valid MeetingRecordParam meetingRecordParam);

    /**
     * 查询所有记录
     */
    List<MeetingRecordOutput> selectAll();

    /**
     * 根据id查询一个记录
     */
    MeetingRecordOutput selectOne(Long id);

    /**
     * 保存参加会议人员
     */
    boolean register(@Valid MeetingRecordParticipatorParam meetingRecordParticipatorParam);

    /**
     * 查找全部（会议参与人员）
     */
    List<MeetingRecordParticipator> allRecordParticipator(Long id);

    /**
     * 删除会议参与人员
     */
    boolean deleteRecordParticipator(Long id);

    /**
     * 增加一条纪要
     */
    MeetingRecordListParam register(@Valid MeetingRecordListParam meetingRecordListParam);

    /**
     * 修改一条纪要
     */
    MeetingRecordListParam updateRecordList(@Valid MeetingRecordListParam meetingRecordListParam);

    /**
     * 删除一条纪要
     */
    boolean deleteRecordList(Long id);
    /**
     * 查询某条纪要
     */
    MeetingRecordListParam findOneList(Long id);

    /**
     * 查询某个会议记录的全部纪要
     */
    List<MeetingRecordListParam> findAllList(Long meetingRecordId);
}
