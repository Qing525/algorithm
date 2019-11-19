package com.wld.modular.system.service;


import com.wld.modular.system.domain.Department;
import com.wld.modular.system.service.dto.DepartmentParam;

import javax.validation.Valid;


public interface DepartmentService {

    Department create(@Valid DepartmentParam wldDepartment);


}
