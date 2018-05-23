package org.omgcms.web.rest.controller;

import org.omgcms.core.exception.CustomSystemException;
import org.omgcms.core.exception.ExceptionCode;
import org.omgcms.core.model.User;
import org.omgcms.core.model.UserRole;
import org.omgcms.core.service.UserRoleService;
import org.omgcms.core.service.UserService;
import org.omgcms.kernel.util.StringPool;
import org.omgcms.security.model.CustomUserDetail;
import org.omgcms.web.constant.MessageKeys;
import org.omgcms.web.util.MessageUtil;
import org.omgcms.web.util.SiteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Madfrog Yang
 * @Date: 2018/4/27 21:42
 * @Description:
 */

@RestController
public class UserAction {

    private static Logger logger = LoggerFactory.getLogger(UserAction.class);

    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/current-user")
    public Object getCurrentUser() {

        Authentication auth = SiteUtil.getAuthentication();
        if (auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {

            UserDetails currentUser = SiteUtil.getLoginUser();

            if (currentUser instanceof CustomUserDetail) {
                CustomUserDetail cud = (CustomUserDetail) currentUser;
                return cud.getUser();
            } else {
                return currentUser;
            }
        }

        return null;
    }


    @GetMapping("/users")
    public Object getUserList(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        Page<User> usersPage = userService.findAll(pageNo, pageSize, "screenName", true);

        return usersPage;

    }

    /**
     * 创建/修改用户
     *
     * @param userId      用户ID（修改用户时设置）
     * @param screenName  账号
     * @param userName    姓名
     * @param email       邮箱地址
     * @param jobTitle    职务
     * @param age         年龄
     * @param birthday    生日
     * @param description 描述
     * @param address     地址
     * @param sex         性别
     * @param password    密码 （需要修改时设置，为空则不处理）
     * @param rePassword  确认密码
     * @return
     */
    @PostMapping("/user")
    public Object createUser(@RequestParam(value = "userId", required = false) Long userId,
                             @RequestParam(value = "screenName") String screenName,
                             @RequestParam(value = "userName") String userName,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "jobTitle", required = false) String jobTitle,
                             @RequestParam(value = "age", defaultValue = "0", required = false) Integer age,
                             @RequestParam(value = "birthday", required = false, defaultValue = "0") Long birthday,
                             @RequestParam(value = "description", required = false) String description,
                             @RequestParam(value = "address", required = false) String address,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "sex", required = false, defaultValue = "0") String sex,
                             @RequestParam(value = "password", required = false) String password,
                             @RequestParam(value = "rePassword", required = false) String rePassword) {
        User user;

        Date now = new Date();

        if (userId != null && userId > 0) {
            user = userService.getUser(userId);

            if (password != null && password.trim().length() > 0) {
                // 用户修改密码
                if (!password.equals(rePassword)) {
                    throw new CustomSystemException(ExceptionCode.ERROR_USER_PASSWORD_DIFFERENT);
                }
                user.setPassword(bCryptPasswordEncoder.encode(password));
            }

        } else {
            // 创建时设置密码
            user = new User();
            user.setCreateDate(now);
            user.setScreenName(screenName);

            String userPassword = StringPool.BLANK;

            if (password != null && password.trim().length() > 0 && password.equals(rePassword)) {
                // 用户设置密码
                userPassword = password;
            } else {
                // 使用默认密码
                //Set Default Password
                String defPasswd = env.getProperty("cms.system.default.password", "123456");
                userPassword = defPasswd;
            }

            user.setPassword(bCryptPasswordEncoder.encode(userPassword));
        }


        user.setUserName(userName);
        user.setJobTitle(jobTitle);
        user.setEmail(email);
        user.setDescription(description);
        user.setAddress(address);
        user.setSex(sex);
        user.setPhone(phone);
        user.setBirthday(new Date(birthday));

        logger.debug("Now:{} Date:{} birthday:{}", now.getTime(), user.getBirthday(), birthday);

        user.setModifyDate(now);

        User savedUser = userService.save(user);

        return savedUser;
    }

    @DeleteMapping("/user")
    public Object deleteUser(@RequestParam(defaultValue = "0") Long userId) {
        userService.delete(userId);
        return MessageUtil.getMessageMap(MessageKeys.MSG_SUCCESS);
    }

    @GetMapping("/user")
    public Object getUserByUserId(@RequestParam(defaultValue = "0") Long userId) {
        User user = userService.getUser(userId);
        return user;
    }

    /**
     * 为用户分配角色
     *
     * @param userId  用户ID
     * @param roleIds 角色ID
     * @return 用户角色关系列表
     */
    @PostMapping("/user/add-roles")
    public Object addRolesToUser(@RequestParam(value = "userId") Long userId,
                                 @RequestParam(value = "roleIds") Long[] roleIds) {

        if (userId == null || userId <= 0) {
            throw new CustomSystemException(ExceptionCode.INVALID_PARAM_MESSAGE, "userId");
        }

        if (roleIds == null || roleIds.length == 0) {
            throw new CustomSystemException(ExceptionCode.INVALID_PARAM_MESSAGE, "roleIds");
        }

        long[] longRoleIds = new long[roleIds.length];
        for (int i = 0; i < roleIds.length; i++) {
            longRoleIds[i] = roleIds[i];
        }

        List<UserRole> userRoles = userRoleService.addUserRoles(userId, longRoleIds);

        return userRoles;


    }

    @GetMapping("/user/get-assigned-roles")
    public Object getAssignedRolesByRoleId(@RequestParam(value = "roleId") Long roleId,
                                           @RequestParam(defaultValue = "1") Integer pageNo,
                                           @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        if (roleId == null || roleId <= 0) {
            throw new CustomSystemException(ExceptionCode.INVALID_PARAM_MESSAGE, "userId");
        }


        Page<User> users = userService.getUsersByRoleId(pageNo, pageSize, "userName", true, roleId);

        return users;
    }


    @GetMapping("/user/get-unassigned-roles")
    public Object getUnassignedRolesByRoleId(@RequestParam(value = "roleId") Long roleId,
                                             @RequestParam(defaultValue = "1") Integer pageNo,
                                             @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        if (roleId == null || roleId <= 0) {
            throw new CustomSystemException(ExceptionCode.INVALID_PARAM_MESSAGE, "userId");
        }

        Page<User> unassignedUsers = userService.getUnassignedRoleUsers(pageNo, pageSize, "userName", true, roleId);
        return unassignedUsers;

    }

}
