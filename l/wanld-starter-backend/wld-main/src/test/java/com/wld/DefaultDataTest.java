package com.wld;


import com.wld.modular.domain.*;
import com.wld.modular.system.repository.*;
import com.wld.modular.system.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultDataTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentTests.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRoleRelationRepository userRoleRelationRepository;
    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RolePermissionRelationRepository rolePermissionRelationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Test
    public void addDefaultRole() {
        Optional<Role> admin = roleRepository.findById(3L);
        Role add = new Role();
        if (!admin.isPresent()) {
            add.setId(3L);
            add.setName("admin");
            add.setIsStatic(true);
            add.setSort(0);
            roleRepository.save(add);
        }

        Optional<Role> test = roleRepository.findById(4L);
        Role add1 = new Role();
        if (!test.isPresent()) {
            add1.setId(4L);
            add1.setName("test");
            add1.setIsStatic(true);
            add1.setSort(0);
            roleRepository.save(add1);
        }
    }

    @Test
    public void addDefaultUser() {
        Optional<User> admin = userRepository.findById(3L);
        User add = new User();
        if (!admin.isPresent()) {
            add.setId(3L);
            add.setUsername("admin");
            add.setPassword(passwordEncoder.encode("123456"));
            add.setNickname("admin");
            add.setEmail("wld@wld.com");
            add.setIsStatic(true);
            userRepository.save(add);
        }
    }


    @Test
    public void addUserRoleRelation() {
        Role role = roleRepository.getOne(3L);
        Role role2 = roleRepository.getOne(4L);

        User user = userRepository.getOne(3L);
        Optional<UserRoleRelation> userRoleRelation = userRoleRelationRepository.findById(3L);
        if (!userRoleRelation.isPresent()) {
            UserRoleRelation userRoleRelation1 = new UserRoleRelation();
            userRoleRelation1.setId(3L);
            userRoleRelation1.setUserId(user.getId());
            userRoleRelation1.setRoleId(role.getId());
            userRoleRelationRepository.save(userRoleRelation1);
        }

        Optional<UserRoleRelation> userRoleRelation2 = userRoleRelationRepository.findById(4L);
        if (!userRoleRelation2.isPresent()) {
            UserRoleRelation userRoleRelation1 = new UserRoleRelation();
            userRoleRelation1.setId(4L);
            userRoleRelation1.setUserId(user.getId());
            userRoleRelation1.setRoleId(role2.getId());
            userRoleRelationRepository.save(userRoleRelation1);
        }
    }

    @Test
    public void addPermission() {
        List<Permission> permissions = new ArrayList<>();
        Permission p1 = new Permission();
        permissions.add(p1);
        p1.setId(10L);
        p1.setName("用户管理");
        p1.setValue("basic:user");
        p1.setType(1);

        Permission p2 = new Permission();
        permissions.add(p2);
        p2.setId(11L);
        p2.setPid(10L);
        p2.setName("添加用户");
        p2.setValue("basic:user:create");
        p2.setType(1);

        Permission p3 = new Permission();
        permissions.add(p3);
        p3.setId(12L);
        p2.setPid(10L);
        p3.setName("删除用户");
        p3.setValue("basic:user:delete");
        p3.setType(1);

        Permission p4 = new Permission();
        permissions.add(p4);
        p4.setId(13L);
        p2.setPid(10L);
        p4.setName("更新用户");
        p4.setValue("basic:user:update");
        p4.setType(1);

        Permission p5 = new Permission();
        permissions.add(p5);
        p5.setId(20L);
        p5.setName("角色管理");
        p5.setValue("basic:role");
        p5.setType(1);

        Permission p6 = new Permission();
        permissions.add(p6);
        p6.setId(21L);
        p2.setPid(20L);
        p6.setName("添加角色");
        p6.setValue("basic:role:create");
        p6.setType(1);

        Permission p7 = new Permission();
        permissions.add(p7);
        p7.setId(22L);
        p2.setPid(20L);
        p7.setName("删除角色");
        p7.setValue("basic:role:delete");
        p7.setType(1);

        Permission p8 = new Permission();
        permissions.add(p8);
        p8.setId(23L);
        p2.setPid(20L);
        p8.setName("更新角色");
        p8.setValue("basic:role:update");
        p8.setType(1);

        permissionRepository.saveAll(permissions);

        Role role = roleRepository.getOne(3L);
        List<RolePermissionRelation> rolePermissionRelations = new ArrayList<>();
        for (Permission permission : permissions) {

            RolePermissionRelation rolePermissionRelation = new RolePermissionRelation();
            rolePermissionRelations.add(rolePermissionRelation);
            rolePermissionRelation.setId(permission.getId());
            rolePermissionRelation.setPermissionId(permission.getId());
            rolePermissionRelation.setRoleId(role.getId());
        }

        rolePermissionRelationRepository.saveAll(rolePermissionRelations);
    }


    @Test
    public void test1() {
        userService.getRoleList(3L);
    }

}
