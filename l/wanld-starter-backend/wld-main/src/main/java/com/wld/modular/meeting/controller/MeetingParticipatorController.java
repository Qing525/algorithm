package com.wld.modular.meeting.controller;

import com.wld.common.api.CommonResult;
import com.wld.modular.meeting.domain.MeetingParticipator;
import com.wld.modular.meeting.service.MeetingAppointmentService;
import com.wld.modular.meeting.service.dto.MeetingParticipatorParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "MeetingParticipatorController", description = "会议邀请人员管理")
@RequestMapping("/api/meeting/appointment/participator")
public class MeetingParticipatorController {
    @Autowired
    MeetingAppointmentService meetingAppointmentService;

    @ApiOperation(value = "添加会议邀请人员")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult registerOne(@RequestBody MeetingParticipatorParam meetingParticipatorParam) {
        boolean b = meetingAppointmentService.register(meetingParticipatorParam);
        if(!b){
            return CommonResult.failed();
        }
        return CommonResult.success(b);
    }

    @ApiOperation("删除会议邀请人员")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public CommonResult deleteParticipator(@PathVariable Long id) {
        boolean b = meetingAppointmentService.deleteParticipator(id);
        if (!b){
            return CommonResult.failed();
        }
        return CommonResult.success(b);
    }
    @ApiOperation("获取某次会议所有邀请人员")
    @RequestMapping(value = "/selectAll/{id}", method = RequestMethod.GET)
    public CommonResult<List<MeetingParticipator>> getAllMeetingParticipator(@PathVariable Long id) {
        List<MeetingParticipator> meetingParticipators = meetingAppointmentService.allParticipator(id);
        return CommonResult.success(meetingParticipators);
    }
}
