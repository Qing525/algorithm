package com.wld.modular.supervision.service.impl;


import com.wld.config.dozer.MapperUtil;
import com.wld.modular.supervision.domain.SupervisionCategory;
import com.wld.modular.supervision.repository.SupervisionCategoryRepository;
import com.wld.modular.supervision.service.SupervisionCategoryService;
import com.wld.modular.supervision.service.dto.SupervisionCategoryDto;
import com.wld.modular.supervision.service.dto.SupervisionCategoryNode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LJQ
 * @date 2019/9/19 10:37
 **/
@Service
public class SupervisionCategoryServiceImpl implements SupervisionCategoryService {
    @Autowired
    private SupervisionCategoryRepository categoryRepository;


    @Override
    public SupervisionCategory create(SupervisionCategoryDto category) {
        SupervisionCategory supervisionCategory = MapperUtil.map(category, SupervisionCategory.class);
        if (null == supervisionCategory.getPid()) {
            supervisionCategory.setPid(0L);
        }
        return categoryRepository.save(supervisionCategory);
    }


    @Override
    public List<SupervisionCategoryNode> treeList() {
        List<SupervisionCategory> categoryList = categoryRepository.findAll();
        List<SupervisionCategoryNode> result = categoryList.stream().filter(category -> category.getPid().equals(0L)).map(category -> covert(category, categoryList)).collect(Collectors.toList());
        return result;
    }

    @Override
    public SupervisionCategory update(SupervisionCategoryDto category) {
        SupervisionCategory supervisionCategory = categoryRepository.findById(category.getId()).get();
        supervisionCategory.setTitle(category.getTitle());
        return categoryRepository.save(supervisionCategory);
    }

    @Override
    public void delete(Long id) {
        List<SupervisionCategory> categoryList = categoryRepository.findByPid(id);
        if (categoryList.size() > 0) {
            throw new RuntimeException("当前分类有子分类，不能删除");
        }
        categoryRepository.deleteById(id);
    }


    private SupervisionCategoryNode covert(SupervisionCategory category, List<SupervisionCategory> categoryList) {
        SupervisionCategoryNode node = new SupervisionCategoryNode();
        BeanUtils.copyProperties(category, node);
        List<SupervisionCategoryNode> children = categoryList.stream()
                .filter(subCategory -> subCategory.getPid().equals(category.getId()))
                .map(subCategory -> covert(subCategory, categoryList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
