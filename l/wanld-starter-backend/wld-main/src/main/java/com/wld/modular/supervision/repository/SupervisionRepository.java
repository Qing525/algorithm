package com.wld.modular.supervision.repository;

import com.wld.modular.supervision.domain.Supervision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author LJQ
 * @date 2019/9/11 14:59
 **/
public interface SupervisionRepository extends JpaRepository<Supervision, Long>, JpaSpecificationExecutor<Supervision> {
    /**
     * 查看当前用户所发起的
     *
     * @param id
     * @param status 0未审核  1通过 2驳回
     * @return
     */
    List<Supervision> findByInitiatorUserIdAndIsReviewEquals(Long id, Integer status);

    /**
     * 查看当前用户所督办的
     *
     * @param id
     * @param status
     * @return
     */
    List<Supervision> findBySuperviseUserIdAndIsReviewEquals(Long id, Integer status);

    /**
     * 查询子任务
     *
     * @param pid
     * @return
     */
    List<Supervision> findByPid(Long pid);
}
