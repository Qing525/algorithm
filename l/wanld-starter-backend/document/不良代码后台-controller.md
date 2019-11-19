
#### [2019/9/24] 返回类型禁用Object，RequestMapping的value必须和方法名一致，格式为get-all-meetingroom，或此处简化为get-all:
``` java
@ApiOperation("获取所有会议室信息")
@RequestMapping(value = "/all", method = RequestMethod.GET)
public CommonResult<List<Map<String, Object>>> getAllMeetingRoom() {
    List<Map<String, Object>> selectAll = meetingRoomService.selectAll();
    return CommonResult.success(selectAll);
}
```

#### [2019/9/24] 禁用使用 CommonResult 作为返回类型必须为 CommonResult<T> 指定具体的返回类型：
``` java
@GetMapping("/accept")
@ApiOperation(value = "接收督办任务")
public CommonResult acceptSupervision(@RequestParam Long supervisionId) {

    return CommonResult.success(supervisionService.receiveSupervision(supervisionId));
}

```

