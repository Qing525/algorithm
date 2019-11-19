package com.wld.modular.system.repository;

import com.wld.modular.system.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 王增光
 * @date 2019/9/5
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {


}
