package com.wld.modular.supervision.service;

import com.wld.modular.supervision.domain.Supervision;
import com.wld.modular.supervision.domain.SupervisionTransfer;
import com.wld.modular.supervision.service.dto.SupervisionDto;

import java.util.List;
import java.util.Map;

/**
 * @author LJQ
 * @date 2019/9/11 14:59
 **/
public interface SupervisionService {
    /**
     * 新建督办任务（状态 设置为未审核）
     *
     * @param supervisionDTO
     * @return
     */
    Supervision create(SupervisionDto supervisionDTO);

    /**
     * 发起人审核
     *
     * @param supervisionId
     * @param status
     * @return
     */
    Supervision review(Long supervisionId, Integer status);


    /**
     * 查看当前用户所发起的
     *
     * @param id
     * @param status
     * @return
     */
    List<SupervisionDto> findByInitiatorUserIdAndIsReviewEquals(Long id, Integer status);

    /**
     * 查看当前用户所督办的
     *
     * @param id
     * @param status
     * @return
     */
    List<SupervisionDto> findBySuperviseUserId(Long id, Integer status);

    /**
     * 查看指派给当前用户的任务
     *
     * @param isReceive  0待接收  1已接收
     * @param isComplete
     * @return
     */
    List<Supervision> queryReceive(Integer isReceive, Integer isComplete);

    /**
     * 撤回分派的任务 (已接收不能撤回)
     *
     * @param id 任务id
     * @return
     */
    Supervision retract(Long id);

    /**
     * 分派任务  发起人审核通过后 督办人才能分派
     *
     * @param supervisionId
     * @param userId
     * @return
     */
    Supervision assign(Long supervisionId, Long userId);

    /**
     * 用户转发任务
     *
     * @param supervisionId
     * @param receiveId
     * @return
     */
    SupervisionTransfer transfer(Long supervisionId, Long receiveId);


    /**
     * 查看二级任务的子任务
     *
     * @param pid
     * @return
     */
    List<Supervision> findByPid(Long pid);

    /**
     * 查看督办任务详情
     *
     * @param id
     * @return
     */
    List<Map<String, Object>> querySupervisionDetail(Long id);

    /**
     * 接收督办任务
     *
     * @param supervisionId
     * @return
     */
    Supervision receiveSupervision(Long supervisionId);

    /**
     * 督办任务完结
     *
     * @param supervisionId
     * @return
     */
    Supervision complete(Long supervisionId);

}
