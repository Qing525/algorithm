package com.wld.modular.meeting.controller;

import com.wld.common.api.CommonResult;
import com.wld.modular.meeting.service.MeetingRecordService;
import com.wld.modular.meeting.service.dto.MeetingRecordListParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "MeetingRecordListController", description = "会议纪要管理")
@RequestMapping("/api/meeting/record/list")
public class MeetingRecordListController {
    @Autowired
    MeetingRecordService meetingRecordService;
    @ApiOperation(value = "添加一条会议纪要")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<MeetingRecordListParam> register(@RequestBody MeetingRecordListParam meetingRecordListParam) {
        MeetingRecordListParam meetingRecordList = meetingRecordService.register(meetingRecordListParam);
        return CommonResult.success(meetingRecordList);
    }
    @ApiOperation("修改一条会议纪要")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult<MeetingRecordListParam> update(@RequestBody MeetingRecordListParam meetingRecordListParam) {
        MeetingRecordListParam meetingRecordList = meetingRecordService.updateRecordList(meetingRecordListParam);
        if (meetingRecordList==null){
            return CommonResult.failed();
        }
        return CommonResult.success(meetingRecordList);
    }
    @ApiOperation("删除一条会议纪要")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult deleteRecordList(@PathVariable Long id) {
        boolean b = meetingRecordService.deleteRecordList(id);
        if(!b){
            CommonResult.failed();
        }
        return CommonResult.success(b);
    }
    @ApiOperation("查询某条会议纪要")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<MeetingRecordListParam> getOneMeetingRecordList(@PathVariable Long id) {
        MeetingRecordListParam meetingRecordList = meetingRecordService.findOneList(id);
        if (meetingRecordList==null){
            return CommonResult.failed();
        }
        return CommonResult.success(meetingRecordList);
    }
    @ApiOperation("获取某次会议的所有会议纪要")
    @RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
    public CommonResult<List<MeetingRecordListParam>> getAllMeetingRecordList(@PathVariable Long id) {
        List<MeetingRecordListParam> meetingRecordLists = meetingRecordService.findAllList(id);
        return CommonResult.success(meetingRecordLists);
    }
}
