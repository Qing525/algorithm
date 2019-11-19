package com.wld.modular.supervision.service;

import com.wld.modular.supervision.domain.SupervisionDelay;
import com.wld.modular.supervision.service.dto.SupervisionDelayDto;

import java.util.List;
import java.util.Map;

/**
 * @author LJQ
 * @date 2019/9/25 15:13
 **/
public interface SupervisionDelayService {
    /**
     * 申请延期
     *
     * @param delayDto
     * @return
     */
    SupervisionDelay apply(SupervisionDelayDto delayDto);

    SupervisionDelay delayReview(Long id, Integer status);

    SupervisionDelayDto findByUserIdAndIsReview(Long id, Integer status);

    List<Map<String, Object>> detail(Long id);
}
