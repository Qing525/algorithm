package com.wld.modular.supervision.service;

import com.wld.modular.supervision.domain.SupervisionCategory;
import com.wld.modular.supervision.service.dto.SupervisionCategoryDto;
import com.wld.modular.supervision.service.dto.SupervisionCategoryNode;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

/**
 * @author LJQ
 * @date 2019/9/19 10:36
 **/
public interface SupervisionCategoryService {

    SupervisionCategory create(SupervisionCategoryDto category);


    List<SupervisionCategoryNode> treeList();


    SupervisionCategory update(SupervisionCategoryDto category);


    @Modifying
    void delete(Long id);


}
