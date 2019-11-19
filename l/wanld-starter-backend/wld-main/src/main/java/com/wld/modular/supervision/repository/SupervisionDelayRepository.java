package com.wld.modular.supervision.repository;

import com.wld.modular.supervision.domain.SupervisionDelay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author LJQ
 * @date 2019/9/25 11:35
 **/
public interface SupervisionDelayRepository extends JpaRepository<SupervisionDelay, Long> {

    List<SupervisionDelay> findBySupervisionId(Long supervisionId);

    @Query(value = "select sd.* from  wld_supervision_delay sd  left join wld_supervision  s" +
            " on sd.supervision_id=s.id where s.initiator_user_id=?1 and sd.is_review=?2 ", nativeQuery = true)
    SupervisionDelay findByUserIdAndIsReview(Long id, Integer status);
}
