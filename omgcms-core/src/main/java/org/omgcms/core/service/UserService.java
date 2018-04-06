package org.omgcms.core.service;

import org.omgcms.core.model.User;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 3:27 2018/4/6
 * @Modified By
 */
public interface UserService {

    // 根据 userId 获取用户信息
    User findByUserId(long userId);

    User findByScreenName(String screenName);

    User findByEmail(String email);


    /**
     * 保存用户
     *
     * @param user 用户对象
     * @return 保存后的用户对象
     */
    User save(User user);

    User getUser(long userId);

    void delete(long userId);

    void delete(User user);

}
