package com.wld.modular.system.service;

import com.wld.config.security.SecurityUserDetails;
import com.wld.modular.domain.Permission;
import com.wld.modular.domain.Role;
import com.wld.modular.domain.User;
import com.wld.modular.system.service.dto.UserLoginParam;
import com.wld.modular.system.service.dto.Userdto;
import com.wld.modular.system.service.dto.UserParam;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


/**
 * 后台管理员Service
 *
 * @author wangzg
 */
public interface UserService {

    Map<String, Object> getLoginUserInfo();

    /**
     * 创建user
     *
     * @param userdto
     * @return
     */
    Userdto create(@Valid Userdto userdto);

    /**
     * 根据用户名获取后台管理员
     */
    User getUserByUsername(String username);

    /**
     * 根据用户名获得 SecurityUserDetails
     *
     * @param username
     * @return SecurityUserDetails， spring security UserDetails 的实现。
     */
    SecurityUserDetails getUserDetails(String username);

    /**
     * 注册功能
     */
    User register(UserParam userParam);

    /**
     * 登录功能
     *
     * @param userLoginParam 登录参数
     * @return 生成的JWT的token
     */
    String login(UserLoginParam userLoginParam);

    /**
     * 刷新token的功能
     *
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    Userdto getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    Page<Userdto> list(String name, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    int update(Long id, User user);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long userId, List<Long> roleIds);

    /**
     * 根据 用户Id 获取 角色列表
     *
     * @param userId
     * @return
     */
    List<Role> getRoleList(Long userId);

    /**
     * 修改用户的+-权限
     *
     * @param userId
     * @param permissionIds
     * @return
     */
    @Transactional
    int updatePermission(Long userId, List<Long> permissionIds);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<Permission> getPermissionList(Long userId);
}
