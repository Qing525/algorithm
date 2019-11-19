package com.wld.modular.supervision.service.impl;


import com.wld.common.util.SerialNumberGenerator;
import com.wld.config.dozer.MapperUtil;
import com.wld.config.security.SecurityUtil;
import com.wld.modular.domain.User;
import com.wld.modular.supervision.domain.*;
import com.wld.modular.supervision.repository.SupervisionCategoryRepository;
import com.wld.modular.supervision.repository.SupervisionDelayRepository;
import com.wld.modular.supervision.repository.SupervisionRepository;
import com.wld.modular.supervision.repository.SupervisionTransferRepository;
import com.wld.modular.supervision.service.SupervisionService;
import com.wld.modular.supervision.service.dto.SupervisionDto;
import com.wld.modular.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.*;

/**
 * @author LJQ
 * @date 2019/9/11 15:00
 **/
@Service
public class SupervisionServiceImpl implements SupervisionService {
    @Autowired
    private SupervisionRepository supervisionRepository;
    @Autowired
    private SupervisionTransferRepository supervisionTransferRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SupervisionDelayRepository delayRepository;
    @Autowired
    private SupervisionCategoryRepository categoryRepository;
    @Autowired
    private SerialNumberGenerator generator;

    @Override
    public Supervision create(SupervisionDto supervisionDTO) {
        Long userId = SecurityUtil.getCurrentUserId();
        Supervision supervision = MapperUtil.map(supervisionDTO, Supervision.class);
        if (null == supervisionDTO.getPid()) {
            supervision.setPid(0L);
            supervision.setIsReview(SupervisionStatus.NOT_REVIEW);
        } else {
            //设置子任务的分类为 父任务的
            Supervision sup = supervisionRepository.findById(supervisionDTO.getPid()).get();
            supervision.setSupervisionCategoryId(sup.getSupervisionCategoryId());
            //子任务不需要审核
            supervision.setIsReview(SupervisionStatus.REVIEW_SUCC);
        }
        supervision.setNumberNo(generator.getSerialNumber());
        supervision.setSuperviseUserId(userId);
        supervision.setIsReceive(SupervisionStatus.INPUT);
        supervision.setIsComplete(SupervisionStatus.NOT_COMPLETE);
        supervision.setCommitTime(new Date());
        return supervisionRepository.save(supervision);
    }

    @Override
    public Supervision review(Long supervisionId, Integer status) {
        Long userId = SecurityUtil.getCurrentUserId();
        Supervision supervision = supervisionRepository.findById(supervisionId).get();
        if (!userId.equals(supervision.getInitiatorUserId())) {
            throw new RuntimeException("非发起人不能进行审核");
        }
        //1.通过 2 驳回
        supervision.setIsReview(status);

        supervision.setSuperviseTime(new Date());

        return supervisionRepository.save(supervision);
    }

    @Override
    public List<SupervisionDto> findByInitiatorUserIdAndIsReviewEquals(Long id, Integer status) {
        List<Supervision> supervisions = supervisionRepository.findByInitiatorUserIdAndIsReviewEquals(id, status);
        return supervisions.size() > 0 ? MapperUtil.map(supervisions, SupervisionDto.class) : null;
    }


    @Override
    public List<SupervisionDto> findBySuperviseUserId(Long id, Integer status) {
        List<Supervision> supervisions = supervisionRepository.findBySuperviseUserIdAndIsReviewEquals(id, status);

        return supervisions.size() > 0 ? MapperUtil.map(supervisions, SupervisionDto.class) : null;

    }


    @Override
    public Supervision retract(Long id) {
        Supervision supervision = supervisionRepository.findById(id).get();
        if (supervision.getReceiveUserId() != null) {
            throw new RuntimeException("已分派的任务不能撤回");
        }
        supervision.setIsReceive(SupervisionStatus.NOT_RECEIVE);
        supervision.setRetractTime(new Date());
        supervision.setReceiveUserId(null);
        return supervisionRepository.save(supervision);
    }

    @Override
    public Supervision assign(Long supervisionId, Long userId) {
        Supervision supervision = supervisionRepository.findById(supervisionId).get();
        if (supervision.getIsReview() != SupervisionStatus.RECEIVED) {
            throw new RuntimeException("未通过审核暂不能分派");
        }
        supervision.setReceiveUserId(userId);
        supervision.setIsReceive(SupervisionStatus.NOT_RECEIVE);
        return supervisionRepository.save(supervision);
    }

    @Override
    public SupervisionTransfer transfer(Long supervisionId, Long receiveId) {
        Long userId = SecurityUtil.getCurrentUserId();
        SupervisionTransfer transfer = supervisionTransferRepository.findBySuperviseId(supervisionId);
        Supervision supervision = supervisionRepository.findById(supervisionId).get();
        if (null != transfer) {
            throw new RuntimeException("任务只能转发一次");
        }
        SupervisionTransfer supervisionTransfer = new SupervisionTransfer();
        supervisionTransfer.setSuperviseId(supervisionId);
        supervisionTransfer.setReceiveUserId(receiveId);
        supervisionTransfer.setTransferUserId(userId);
        //同时设置督办任务表的状态为未接收 修改负责人
        supervision.setIsReceive(SupervisionStatus.NOT_RECEIVE);
        supervision.setReceiveUserId(receiveId);
        supervisionRepository.save(supervision);
        return supervisionTransferRepository.save(supervisionTransfer);
    }


    @Override
    public List<Supervision> findByPid(Long pid) {
        return supervisionRepository.findByPid(pid);
    }

    @Override
    public List<Map<String, Object>> querySupervisionDetail(Long id) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Supervision supervision = supervisionRepository.findById(id).get();
        SupervisionCategory category = categoryRepository.findById(supervision.getSupervisionCategoryId()).get();
        map.put("supervisionDto", MapperUtil.map(supervision, SupervisionDto.class));
        User initiator = userRepository.findById(supervision.getInitiatorUserId()).get();
        if (null != supervision.getReceiveUserId()) {
            User receiver = userRepository.findById(supervision.getReceiveUserId()).get();
            map.put("receiver", receiver.getUsername());
        }
        User supervisor = userRepository.findById(supervision.getSuperviseUserId()).get();
        map.put("initiator", initiator.getUsername());
        map.put("supervisor", supervisor.getUsername());
        map.put("receiver", null);
        map.put("categoryName", category.getTitle());
        list.add(map);
        return list;
    }


    @Override
    public Supervision receiveSupervision(Long supervisionId) {

        Supervision supervision = supervisionRepository.findById(supervisionId).get();
        supervision.setIsReceive(SupervisionStatus.RECEIVED);
        return supervisionRepository.save(supervision);
    }

    @Override
    public Supervision complete(Long supervisionId) {
        Supervision supervision = supervisionRepository.findById(supervisionId).get();
        List<SupervisionDelay> delayList = delayRepository.findBySupervisionId(supervisionId);
        if (delayList.size() > 0) {
            supervision.setIsComplete(SupervisionStatus.DELAY_COMPLETED);
        }
        supervision.setIsComplete(SupervisionStatus.COMPLETED);
        return supervisionRepository.save(supervision);
    }

    @Override
    public List<Supervision> queryReceive(Integer isReceive, Integer isComplete) {
        Long userId = SecurityUtil.getCurrentUserId();
        return supervisionRepository.findAll((Specification<Supervision>) (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(cb.equal(root.get("receiveUserId").as(Long.class), userId));
            if (null != isReceive) {
                list.add(cb.equal(root.get("isReceive").as(Integer.class), isReceive));
            }
            if (null != isComplete) {
                list.add(cb.equal(root.get("isComplete").as(Integer.class), isComplete));
            }
            query.where(cb.and(list.toArray(new Predicate[list.size()])));
            return query.getRestriction();
        });
    }
}
