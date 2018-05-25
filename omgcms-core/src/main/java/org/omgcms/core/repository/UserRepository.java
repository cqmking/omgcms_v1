package org.omgcms.core.repository;

import org.omgcms.core.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 3:24 2018/4/6
 * @Modified By
 */
@Repository
public interface UserRepository extends JpaSpecificationExecutor<User>, JpaRepository<User, Long> {

    /**
     * 根据 userId 获取用户信息
     *
     * @param userId
     * @return
     */
    User findByUserId(long userId);

    User findByScreenName(String screenName);

    User findByEmail(String email);

    /**
     * 模糊查询用户，根据：账号、名称、邮件
     * @param screenName
     * @param userName
     * @param email
     * @param pageable
     * @return
     */
    Page<User> findByScreenNameLikeAndUserNameLikeAndEmailLike(String screenName, String userName, String email, Pageable pageable);

}
