package com.wld.modular.supervision.repository;

import com.wld.modular.supervision.domain.SupervisionTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LJQ
 * @date 2019/9/16 14:39
 **/
public interface SupervisionTransferRepository extends JpaRepository<SupervisionTransfer, Long> {
    /**
     * 根据督办任务id查询
     *
     * @param id
     * @return
     */
    SupervisionTransfer findBySuperviseId(Long id);
}
