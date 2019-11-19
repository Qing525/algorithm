package com.wld.modular.system.repository;

import com.wld.modular.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @author ZCF
 **/
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * 根据用户名或邮箱查询是否存在用户
     *
     * @param userName
     * @param email
     * @return
     */
    int countByUsernameOrEmail(String userName, String email);

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    @Nullable
    User findByUsername(String username);


    /**
     * 根据用户名或昵称查找用户
     *
     * @param pageable
     * @param name1
     * @param name2
     * @return
     */
    Page<User> findByUsernameLikeOrNicknameLike(Pageable pageable, String name1, String name2);

    /**
     * 根据id集合返回user  list
     * @param ids
     * @return
     */
    List<User> findByIdIn(List<Long> ids);
}
