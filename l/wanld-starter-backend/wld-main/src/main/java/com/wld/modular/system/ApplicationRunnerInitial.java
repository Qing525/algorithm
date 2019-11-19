package com.wld.modular.system;

import com.wld.config.PermissionList;
import com.wld.modular.domain.*;
import com.wld.modular.system.repository.*;
import com.wld.modular.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * 初始始化admin,等数据
 */
@Component
public class ApplicationRunnerInitial implements ApplicationRunner {

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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        adminRole();
        adminUser();
        adminUserRoleRelation();
        permission();
        adminRolePermission();
        System.out.println("初始化数据完成！");
    }

    private void adminRole() {
        Optional<Role> admin = roleRepository.findById(3L);
        if (!admin.isPresent()) {
            Role add = new Role();
            add.setId(3L);
            add.setName("管理员");
            add.setValue("ROLE_ADMIN");
            add.setIsStatic(true);
            add.setSort(0);
            add.setDescription("管理员账号");
            roleRepository.save(add);
        }
    }

    private void adminUser() {
        Optional<User> admin = userRepository.findById(3L);
        if (!admin.isPresent()) {
            User add = new User();
            add.setId(3L);
            add.setUsername("admin");
            add.setPassword(passwordEncoder.encode("123456"));
            add.setNickname("admin");
            add.setEmail("wld@wld.com");
            add.setIsStatic(true);
            userRepository.save(add);
        }
    }

    public void adminUserRoleRelation() {
        Optional<UserRoleRelation> userRoleRelation = userRoleRelationRepository.findById(3L);
        if (!userRoleRelation.isPresent()) {
            UserRoleRelation add = new UserRoleRelation();
            add.setId(3L);
            add.setUserId(3L);
            add.setRoleId(3L);
            userRoleRelationRepository.save(add);
        }
    }

    public void permission() {
        List<Permission> adds = new ArrayList<>();
        if (!permissionRepository.findById(10L).isPresent()) {
            Permission p1 = new Permission();
            p1.setPid(0L);
            p1.setId(10L);
            p1.setName("用户管理");
            p1.setValue(PermissionList.USER);
            p1.setType(1);
            adds.add(p1);
        }
        if (!permissionRepository.findById(11L).isPresent()) {
            Permission p2 = new Permission();
            p2.setId(11L);
            p2.setPid(10L);
            p2.setName("新增用户");
            p2.setValue(PermissionList.USER_CREATE);
            p2.setType(2);
            adds.add(p2);
        }
        if (!permissionRepository.findById(12L).isPresent()) {
            Permission p3 = new Permission();
            p3.setId(12L);
            p3.setPid(10L);
            p3.setName("删除用户");
            p3.setValue(PermissionList.USER_DELETE);
            p3.setType(2);
            adds.add(p3);
        }

        if (!permissionRepository.findById(13L).isPresent()) {
            Permission p4 = new Permission();
            p4.setId(13L);
            p4.setPid(10L);
            p4.setName("更新用户");
            p4.setValue(PermissionList.USER_UPDATE);
            p4.setType(2);
            adds.add(p4);
        }
        if (!permissionRepository.findById(14L).isPresent()) {
            Permission p14 = new Permission();
            p14.setId(14L);
            p14.setPid(10L);
            p14.setName("查看用户");
            p14.setValue(PermissionList.USER_GET);
            p14.setType(2);
            adds.add(p14);
        }

        if (!permissionRepository.findById(20L).isPresent()) {
            Permission p20 = new Permission();
            p20.setPid(0L);
            p20.setId(20L);
            p20.setName("角色管理");
            p20.setValue(PermissionList.ROLE);
            p20.setType(1);
            adds.add(p20);
        }

        if (!permissionRepository.findById(21L).isPresent()) {
            Permission role = new Permission();
            role.setId(21L);
            role.setPid(20L);
            role.setName("新增角色");
            role.setValue(PermissionList.ROLE_CREATE);
            role.setType(2);
            adds.add(role);
        }
        if (!permissionRepository.findById(22L).isPresent()) {
            Permission role = new Permission();
            role.setId(22L);
            role.setPid(20L);
            role.setName("删除角色");
            role.setValue(PermissionList.ROLE_DELETE);
            role.setType(2);
            adds.add(role);
        }
        if (!permissionRepository.findById(23L).isPresent()) {
            Permission role = new Permission();
            role.setId(23L);
            role.setPid(20L);
            role.setName("更新角色");
            role.setValue(PermissionList.ROLE_UPDATE);
            role.setType(2);
            adds.add(role);
        }
        if (!permissionRepository.findById(24L).isPresent()) {
            Permission role = new Permission();
            role.setId(24L);
            role.setPid(20L);
            role.setName("查看角色");
            role.setValue(PermissionList.ROLE_GET);
            role.setType(2);
            adds.add(role);
        }
        permissionRepository.saveAll(adds);
    }

    public void adminRolePermission() {
        List<Permission> list = permissionRepository.findAll();
        List<RolePermissionRelation> relations = rolePermissionRelationRepository.findAll();

        List<RolePermissionRelation> adds = new ArrayList<>();
        for (Permission permission : list) {
            boolean has = false;
            for (RolePermissionRelation relation : relations) {
                if (relation.getRoleId() == 3L && relation.getPermissionId().equals(permission.getId())) {
                    has = true;
                    break;
                }
            }
            if (!has) {
                RolePermissionRelation relation = new RolePermissionRelation();
                relation.setPermissionId(permission.getId());
                relation.setRoleId(3L);
                adds.add(relation);
            }
        }
        rolePermissionRelationRepository.saveAll(adds);
    }

}
