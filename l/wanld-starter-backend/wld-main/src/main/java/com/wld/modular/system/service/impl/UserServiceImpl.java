package com.wld.modular.system.service.impl;

import com.wld.config.dozer.MapperUtil;
import com.wld.config.security.SecurityUserDetails;
import com.wld.config.security.SecurityUtil;
import com.wld.config.security.jwt.JwtTokenProvider;
import com.wld.modular.domain.*;
import com.wld.modular.system.domain.UserLoginLog;
import com.wld.modular.system.repository.*;
import com.wld.modular.system.service.UserService;
import com.wld.modular.system.service.dto.UserLoginParam;
import com.wld.modular.system.service.dto.Userdto;
import com.wld.modular.system.service.dto.UserParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * UserService
 *
 * @author wangzg
 */
@Service
@Validated
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${default.password}")
    private String defaultPassword;

    @Autowired
    private UserRoleRelationRepository userRoleRelationRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserPermissionRelationRepository userPermissionRelationRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLoginLogRepository userLoginLogRepository;

    @Override
    public Map<String, Object> getLoginUserInfo() {
        Map<String, Object> data = new HashMap<>();

        Optional<SecurityUserDetails> user = SecurityUtil.getCurrentUser();

        if (user.isPresent()) {
            Userdto userdto = MapperUtil.map(user.get().getUser(), Userdto.class);
            data.put("user", userdto);
        } else {
            data.put("user", null);
        }
        return data;
    }

    @Override
    public Userdto create(@Valid Userdto userdto) {
        int count = userRepository.countByUsernameOrEmail(userdto.getUsername(), userdto.getEmail());
        if (count > 0) {
            return userdto;
        }
        User user = MapperUtil.map(userdto, User.class);
        //默认密码123456
        user.setPassword(passwordEncoder.encode(defaultPassword));
        user = userRepository.save(user);
        userdto.setId(user.getId());
        return userdto;
    }


    @Cacheable(value = "user", key = "#username", condition = "#result!=null")
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public SecurityUserDetails getUserDetails(String username) {
        User user = getUserByUsername(username);
        if (user != null) {
            List<Permission> permissionList = getPermissionList(user.getId());
            return new SecurityUserDetails(user, permissionList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public User register(UserParam userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        //查询是否有相同用户名的用户
        String name = user.getUsername();
        User findUser = userRepository.findByUsername(name);
        if (findUser != null) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userRepository.save(user);
        return user;
    }

    @Override
    public String login(UserLoginParam userLoginParam) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            SecurityUserDetails userDetails = getUserDetails(userLoginParam.getUsername());
            if (!passwordEncoder.matches(userLoginParam.getPassword(), userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenProvider.generateToken(userDetails, userLoginParam.isRememberMe());
            updateUserLoginTime(userDetails.getUserId());
            insertLoginLog(userDetails.getUserId());
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     *
     * @param userId 用户id
     */
    private void insertLoginLog(Long userId) {
        UserLoginLog loginLog = new UserLoginLog();
        loginLog.setUserId(userId);
        loginLog.setCreatedTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            loginLog.setIp(request.getRemoteAddr());
        }
        userLoginLogRepository.save(loginLog);
    }

    /**
     * 根据用户名修改登录时间
     */
    private void updateUserLoginTime(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().setLoginTime(new Date());
            userRepository.save(user.get());
        }
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring(tokenHead.length());
        if (jwtTokenProvider.canRefresh(token)) {
            return jwtTokenProvider.refreshToken(token);
        }
        return null;
    }

    @Override
    public Userdto getItem(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return MapperUtil.map(user, Userdto.class);
        }
        return null;
    }

    @Override
    public Page<Userdto> list(String name, Integer pageSize, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        if (StringUtils.isEmpty(name)) {
            return MapperUtil.map(userRepository.findAll(pageable), Userdto.class);
        }

        Page<User> findbyname = userRepository.findByUsernameLikeOrNicknameLike(pageable, "%" + name + "%", "%" + name + "%");

        return MapperUtil.map(findbyname, Userdto.class);
    }

    @CachePut(value = "user", key = "#result.username")
    @Override
    public int update(Long id, User user) {
        if (user != null) {
            user.setId(id);
            userRepository.save(user);
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return 0;
        }
        user.get().setIsDeleted(true);
        userRepository.save(user.get());
        return 1;
    }

    /**
     * 更新用户角色：删除已存在的角色并重新添加。
     *
     * @param userId
     * @param roleIds
     * @return
     */
    @Override
    public int updateRole(Long userId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
//        //先删除原来的关系
        List<UserRoleRelation> userRoleRelationList = userRoleRelationRepository.findByUserId(userId);
        if (!userRoleRelationList.isEmpty()) {
            userRoleRelationRepository.deleteAll(userRoleRelationList);
        }

        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UserRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UserRoleRelation roleRelation = new UserRoleRelation();
                roleRelation.setRoleId(roleId);
                roleRelation.setUserId(userId);
                list.add(roleRelation);
            }
            userRoleRelationRepository.saveAll(list);
        }
        return count;
    }

    @Override
    public List<Role> getRoleList(Long userId) {
        List<UserRoleRelation> userRoleRelations = userRoleRelationRepository.findByUserId(userId);
        List<Long> roleIds = userRoleRelations.stream().map(UserRoleRelation::getRoleId).collect(Collectors.toList());

        return roleRepository.findAllById(roleIds);

    }

    @Override
    public int updatePermission(Long userId, List<Long> permissionIds) {
        //删除原所有权限关系
        List<UserPermissionRelation> permissionRelationList = userPermissionRelationRepository.findWldUserPermissionRelationsByUserId(userId);
        if (!permissionRelationList.isEmpty()) {
            userPermissionRelationRepository.deleteAll(permissionRelationList);
        }

        //获取用户所有角色权限
        List<Permission> permissionList = permissionRepository.getRolePermissionList(userId);

        List<Long> rolePermissionList = permissionList.stream().map(Permission::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(permissionIds)) {
            List<UserPermissionRelation> relationList = new ArrayList<>();
            //筛选出+权限
            List<Long> addPermissionIdList = permissionIds.stream().filter(permissionId -> !rolePermissionList.contains(permissionId)).collect(Collectors.toList());
            //筛选出-权限
            List<Long> subPermissionIdList = rolePermissionList.stream().filter(permissionId -> !permissionIds.contains(permissionId)).collect(Collectors.toList());
            //插入+-权限关系
            relationList.addAll(convert(userId, 1, addPermissionIdList));
            relationList.addAll(convert(userId, -1, subPermissionIdList));
            userPermissionRelationRepository.saveAll(relationList);
            return permissionIds.size();
        }
        return 0;
    }

    /**
     * 将+-权限关系转化为对象
     */
    private List<UserPermissionRelation> convert(Long userId, Integer type, List<Long> permissionIdList) {
        List<Permission> permissions = permissionRepository.findAllById(permissionIdList);
        List<UserPermissionRelation> relationList = permissions.stream().map(permission -> {
            UserPermissionRelation relation = new UserPermissionRelation();
            relation.setUserId(userId);
            relation.setType(type);
            relation.setPermissionId(permission.getId());
            return relation;
        }).collect(Collectors.toList());
        return relationList;
    }

    @Override
    public List<Permission> getPermissionList(Long userId) {
        return permissionRepository.getPermissionList(userId);
    }
}
