package com.wld.modular.supervision.service.impl;

import com.wld.config.dozer.MapperUtil;
import com.wld.modular.domain.User;
import com.wld.modular.supervision.domain.Supervision;
import com.wld.modular.supervision.domain.SupervisionDelay;
import com.wld.modular.supervision.domain.SupervisionStatus;
import com.wld.modular.supervision.repository.SupervisionDelayRepository;
import com.wld.modular.supervision.repository.SupervisionRepository;
import com.wld.modular.supervision.service.SupervisionDelayService;
import com.wld.modular.supervision.service.dto.SupervisionDelayDto;
import com.wld.modular.supervision.service.dto.SupervisionDto;
import com.wld.modular.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LJQ
 * @date 2019/9/25 15:13
 **/
@Service
public class SupervisionDelayServiceImpl implements SupervisionDelayService {
    @Autowired
    private SupervisionDelayRepository delayRepository;
    @Autowired
    private SupervisionRepository supervisionRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public SupervisionDelay apply(SupervisionDelayDto delayDto) {
        //判断是否有未完成的申请
        List<SupervisionDelay> delayList = delayRepository.findBySupervisionId(delayDto.getSupervisionId());
        delayList.stream().filter(supervisionDelay -> supervisionDelay.getIsReview() == 0).forEach(supervisionDelay -> {
            throw new RuntimeException("当前有未完成的申请，暂不能申请");
        });
        SupervisionDelay delay = MapperUtil.map(delayDto, SupervisionDelay.class);
        delay.setIsReview(SupervisionStatus.NOT_REVIEW);
        return delayRepository.save(delay);
    }

    @Override
    public SupervisionDelay delayReview(Long id, Integer status) {
        SupervisionDelay delay = delayRepository.findById(id).get();
        Supervision supervision = supervisionRepository.findById(delay.getSupervisionId()).get();
        if (status == SupervisionStatus.REVIEW_SUCC) {
            supervision.setEndTime(delay.getEndTime());
        }
        delay.setIsReview(status);
        supervisionRepository.save(supervision);
        return delayRepository.save(delay);
    }

    @Override
    public SupervisionDelayDto findByUserIdAndIsReview(Long id, Integer status) {
        SupervisionDelay delay = delayRepository.findByUserIdAndIsReview(id, status);
        return delay == null ? null : MapperUtil.map(delay, SupervisionDelayDto.class);
    }

    @Override
    public List<Map<String, Object>> detail(Long id) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        SupervisionDelay delay = delayRepository.findById(id).get();
        Supervision supervision = supervisionRepository.findById(delay.getSupervisionId()).get();
        User initiator = userRepository.findById(supervision.getInitiatorUserId()).get();
        User receiver = userRepository.findById(supervision.getReceiveUserId()).get();
        User supervisor = userRepository.findById(supervision.getSuperviseUserId()).get();
        map.put("supervisionDto", MapperUtil.map(supervision, SupervisionDto.class));
        map.put("delay", delay);
        map.put("receiver", receiver.getUsername());
        map.put("initiator", initiator.getUsername());
        map.put("supervisor", supervisor.getUsername());
        list.add(map);
        return list;
    }
}
