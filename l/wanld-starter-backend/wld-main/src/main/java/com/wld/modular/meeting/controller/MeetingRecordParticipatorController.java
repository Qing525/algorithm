package com.wld.modular.meeting.controller;

import com.wld.common.api.CommonResult;
import com.wld.modular.meeting.service.MeetingRecordService;
import com.wld.modular.meeting.service.dto.MeetingRecordParticipatorParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "MeetingRecordParticipatorController", description = "会议参与人员管理")
@RequestMapping("/api/meeting/record/participator")
public class MeetingRecordParticipatorController {
    @Autowired
    MeetingRecordService meetingRecordService;

    @ApiOperation(value = "添加参加会议人员")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult registerOne(@RequestBody MeetingRecordParticipatorParam meetingRecordParticipatorParam) {
        boolean b = meetingRecordService.register(meetingRecordParticipatorParam);
        if (!b){
            return CommonResult.failed();
        }
        return CommonResult.success(b);
    }

    @ApiOperation("删除参加会议人员")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult deleteRecordParticipator(@PathVariable Long id) {
        boolean b = meetingRecordService.deleteRecordParticipator(id);
        if (!b){
            return CommonResult.failed();
        }
        return CommonResult.success(b);
    }
    @ApiOperation("获取某次会议参加会议人员")
    @RequestMapping(value = "/select_all/{id}", method = RequestMethod.GET)
    public CommonResult<List<com.wld.modular.meeting.domain.MeetingRecordParticipator>> getAllMeetingRecordParticipator(@PathVariable Long id) {
        List<com.wld.modular.meeting.domain.MeetingRecordParticipator> meetingRecordParticipators = meetingRecordService.allRecordParticipator(id);
        return CommonResult.success(meetingRecordParticipators);
    }
}
