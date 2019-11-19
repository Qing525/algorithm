package com.wld.modular.meeting.controller;

import com.wld.common.api.CommonResult;
import com.wld.modular.meeting.service.MeetingRoomService;
import com.wld.modular.meeting.service.dto.MeetingRoomOutput;
import com.wld.modular.meeting.service.dto.MeetingRoomParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "MeetingRoomController", description = "会议室管理")
@RequestMapping("/api/meeting/room")
public class MeetingRoomController {
    @Autowired
    MeetingRoomService meetingRoomService;

    @ApiOperation(value = "添加会议室")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<MeetingRoomParam> register(@RequestBody MeetingRoomParam meetingRoomParam) {
        MeetingRoomParam register = meetingRoomService.register(meetingRoomParam);
        return CommonResult.success(register);
    }
    @ApiOperation("删除会议室")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id) {
        boolean b = meetingRoomService.delete(id);
        if(!b){
            CommonResult.failed();
        }
        return CommonResult.success(b);
    }
    @ApiOperation("修改会议室信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult<MeetingRoomParam> update(@RequestBody MeetingRoomParam meetingRoomParam) {
        MeetingRoomParam meetingRoom = meetingRoomService.update(meetingRoomParam);
        if (meetingRoom==null){
            return CommonResult.failed();
        }
        return CommonResult.success(meetingRoom);
    }
    @ApiOperation("获取所有会议室信息")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public CommonResult<List<MeetingRoomOutput>> getAllMeetingRoom() {
        List<MeetingRoomOutput> selectAll = meetingRoomService.selectAll();
        return CommonResult.success(selectAll);
    }
    @ApiOperation("获取指定会议室信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<MeetingRoomOutput> getOneMeetingRoom(@PathVariable Long id) {
        MeetingRoomOutput meetingRoom = meetingRoomService.selectOne(id);
        if (meetingRoom==null){
            return CommonResult.failed();
        }
        return CommonResult.success(meetingRoom);
    }
}
