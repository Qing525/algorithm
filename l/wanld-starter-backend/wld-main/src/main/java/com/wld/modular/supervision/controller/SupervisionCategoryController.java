package com.wld.modular.supervision.controller;

import com.wld.common.api.CommonResult;
import com.wld.config.dozer.MapperUtil;
import com.wld.modular.supervision.domain.SupervisionCategory;
import com.wld.modular.supervision.service.SupervisionCategoryService;
import com.wld.modular.supervision.service.dto.SupervisionCategoryDto;
import com.wld.modular.supervision.service.dto.SupervisionCategoryNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LJQ
 * @date 2019/9/19 11:35
 **/
@RestController
@Api(tags = "sup-督办任务分类")
@RequestMapping("/api/supervision/category")
public class SupervisionCategoryController {
    @Autowired
    private SupervisionCategoryService categoryService;

    @GetMapping("/list")
    @ApiOperation(value = " 分类层次列表")
    public CommonResult<List<SupervisionCategoryNode>> categoryList() {
        return CommonResult.success(categoryService.treeList());
    }

    @PostMapping("/create")
    @ApiOperation(value = "新增分类")
    public CommonResult<SupervisionCategoryDto> create(@RequestBody SupervisionCategoryDto category) {
        SupervisionCategory supervisionCategory = categoryService.create(category);
        return CommonResult.success(MapperUtil.map(supervisionCategory, SupervisionCategoryDto.class));
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改分类名称")
    public CommonResult<SupervisionCategoryDto> update(@RequestBody SupervisionCategoryDto category) {
        SupervisionCategory supervisionCategory = categoryService.update(category);
        return CommonResult.success(MapperUtil.map(supervisionCategory, SupervisionCategoryDto.class));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除分类")
    public CommonResult<String> delete(@RequestParam Long id) {
        categoryService.delete(id);
        return CommonResult.success("删除成功");
    }
}
