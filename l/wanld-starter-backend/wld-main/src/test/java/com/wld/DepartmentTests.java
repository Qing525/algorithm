package com.wld;


import com.wld.modular.system.domain.Department;
import com.wld.modular.system.repository.DepartmentRepository;
import com.wld.modular.system.service.DepartmentService;
import com.wld.modular.system.service.UserService;
import com.wld.modular.system.service.dto.DepartmentParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentTests {


    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentTests.class);

    @Autowired
    private DepartmentRepository departmentRepository;


    @Test
    public void TestCreate() {


        DepartmentParam department = new DepartmentParam();
        department.setId(11L);
        department.setName("我是一个名字很长的部门");
        Department result = departmentService.create(department);
        LOGGER.info(result.getId().toString());
    }

    @Test
    public void TestQuery() {
        List<Department> department = departmentRepository.findAll();
    }

    @Test
    public void TestSoftDelete() {

        List<Department> departments = departmentRepository.findAll();
        departments.get(0).setIsDeleted(true);

    }
}
