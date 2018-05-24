package org.omgcms.core.service;

import org.omgcms.core.model.User;
import org.springframework.data.domain.Page;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 3:27 2018/4/6
 * @Modified By
 */
public interface UserService {

    /**
     * 根据 userId 获取用户信息
     *
     * @param userId 用户ID
     * @return
     */
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

    /**
     * 批量删除用户
     * @param userIds 用户账号ID数组
     */
    void deleteInBatch(long[] userIds);

    void delete(User user);

    /**
     * 查找所有用户信息
     *
     * @param pageNo          页码
     * @param pageSize        每页显示条数
     * @param orderByProperty 排序字段
     * @param isAsc           是否升序排序
     * @return
     */
    Page<User> findAll(int pageNo, int pageSize, String orderByProperty, boolean isAsc);

    /**
     * 根据角色ID获取用户列表
     *
     * @param pageNo          当前页
     * @param pageSize        每页显示条数
     * @param orderByProperty 排序字段
     * @param isAsc        排序类型— 升序、降序
     * @param roleId          角色ID
     * @return
     */
    Page<User> getUsersByRoleId(int pageNo, int pageSize, String orderByProperty, boolean isAsc, long roleId);

    /**
     * 获取不具备某一角色的用户列表
     *
     * @param pageNo          当前页
     * @param pageSize        每页显示条数
     * @param orderByProperty 排序字段
     * @param isAsc        排序类型— 升序、降序
     * @param roleId          角色ID
     * @return
     */
    Page<User> getUnassignedRoleUsers(int pageNo, int pageSize, String orderByProperty, boolean isAsc, long roleId);
}
