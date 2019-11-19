package com.wld.modular.system.service.impl;

import com.wld.modular.system.domain.Department;
import com.wld.modular.system.repository.DepartmentRepository;
import com.wld.modular.system.service.DepartmentService;
import com.wld.modular.system.service.dto.DepartmentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Date;

@Service
@Validated
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department create(@Valid DepartmentParam wldDepartment) {
        Department department = new Department();
        department.setName(wldDepartment.getName());
        department.setCreatedTime(new Date());
        return departmentRepository.save(department);
    }
}
