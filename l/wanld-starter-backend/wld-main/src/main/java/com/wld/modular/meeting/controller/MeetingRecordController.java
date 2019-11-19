package com.wld.modular.meeting.controller;

import com.wld.common.api.CommonResult;
import com.wld.modular.meeting.service.MeetingRecordService;
import com.wld.modular.meeting.service.dto.MeetingRecordOutput;
import com.wld.modular.meeting.service.dto.MeetingRecordParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "MeetingRecordController", description = "会议记录管理")
@RequestMapping("/api/meeting/record")
public class MeetingRecordController {
    @Autowired
    MeetingRecordService meetingRecordService;
    @ApiOperation(value = "添加会议记录")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<MeetingRecordParam> register(@RequestBody MeetingRecordParam meetingRecordParam) {
        MeetingRecordParam meetingRecord = meetingRecordService.register(meetingRecordParam);
        return CommonResult.success(meetingRecord);
    }
    @ApiOperation("删除会议记录")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id) {
        boolean b = meetingRecordService.delete(id);
        if(!b){
            CommonResult.failed();
        }
        return CommonResult.success(b);
    }
    @ApiOperation("修改会议记录信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult<MeetingRecordParam> update(@RequestBody MeetingRecordParam meetingAppointmentParam) {
        MeetingRecordParam meetingRecord = meetingRecordService.update(meetingAppointmentParam);
        if (meetingRecord==null){
            return CommonResult.failed();
        }
        return CommonResult.success(meetingRecord);
    }
    @ApiOperation("获取所有会议记录")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public CommonResult<List<MeetingRecordOutput>> getAllMeetingRecord() {
        List<MeetingRecordOutput> MeetingRecordOutputs = meetingRecordService.selectAll();
        return CommonResult.success(MeetingRecordOutputs);
    }
    @ApiOperation("获取指定会议记录")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<MeetingRecordOutput> getOneMeetingRecord(@PathVariable Long id) {
        MeetingRecordOutput meetingRecord = meetingRecordService.selectOne(id);
        if (meetingRecord==null){
            return CommonResult.failed();
        }
        return CommonResult.success(meetingRecord);
    }
}
