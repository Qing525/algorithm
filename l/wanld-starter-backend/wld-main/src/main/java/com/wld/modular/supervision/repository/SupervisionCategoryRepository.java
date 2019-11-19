package com.wld.modular.supervision.repository;

import com.wld.modular.supervision.domain.SupervisionCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LJQ
 * @date 2019/9/19 9:51
 **/
public interface SupervisionCategoryRepository extends JpaRepository<SupervisionCategory, Long> {

    List<SupervisionCategory> findByPid(Long id);

}
