package com.wld.modular.meeting.controller;

import com.wld.common.api.CommonResult;
import com.wld.modular.meeting.service.MeetingAppointmentService;
import com.wld.modular.meeting.service.dto.MeetingAppointmentOutput;
import com.wld.modular.meeting.service.dto.MeetingAppointmentParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "MeetingAppointmentController", description = "会议预约管理")
@RequestMapping("/api/meeting/appointment")
public class MeetingAppointmentController {
    @Autowired
    MeetingAppointmentService meetingAppointmentService;

    @ApiOperation(value = "添加会议预约")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<MeetingAppointmentParam> register(@RequestBody MeetingAppointmentParam meetingAppointmentParam) {
        MeetingAppointmentParam meetingAppointmentParam1 = meetingAppointmentService.register(meetingAppointmentParam);
        return CommonResult.success(meetingAppointmentParam1);
    }
    @ApiOperation("删除会议预约")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id) {
        boolean b = meetingAppointmentService.delete(id);
        if(!b){
            CommonResult.failed();
        }
        return CommonResult.success(b);
    }
    @ApiOperation("修改会议预约信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult<MeetingAppointmentParam> update(@RequestBody MeetingAppointmentParam meetingAppointmentParam) {
        MeetingAppointmentParam meetingAppointment = meetingAppointmentService.update(meetingAppointmentParam);
        if (meetingAppointment==null){
            return CommonResult.failed();
        }
        return CommonResult.success(meetingAppointment);
    }
    @ApiOperation("获取所有会议室预约信息")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public CommonResult<List<MeetingAppointmentOutput>> getAllMeetingAppointment() {
        List<MeetingAppointmentOutput> meetingAppointments = meetingAppointmentService.selectAll();
        return CommonResult.success(meetingAppointments);
    }
    @ApiOperation("获取指定会议室信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<MeetingAppointmentOutput> getOneMeetingAppointment(@PathVariable Long id) {
        MeetingAppointmentOutput meetingAppointment = meetingAppointmentService.selectOne(id);
        if (meetingAppointment==null){
            return CommonResult.failed();
        }
        return CommonResult.success(meetingAppointment);
    }
}
