package com.wld.modular.supervision.service.dto;

import com.wld.modular.supervision.domain.SupervisionCategory;

import java.util.List;

/**
 * @author LJQ
 * @date 2019/9/19 11:11
 **/
public class SupervisionCategoryNode extends SupervisionCategory {

    private List<SupervisionCategoryNode> children;

    public List<SupervisionCategoryNode> getChildren() {
        return children;
    }

    public void setChildren(List<SupervisionCategoryNode> children) {
        this.children = children;
    }
}
