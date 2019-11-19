package com.wld.modular.system.repository;

import com.wld.modular.system.domain.UserLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ZCF
 * @date 2019/8/28 9:44
 **/
public interface UserLoginLogRepository extends JpaRepository<UserLoginLog, Long> {
}
