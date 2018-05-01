package org.omgcms.web.rest.controller;

import org.omgcms.core.exception.CustomSystemException;
import org.omgcms.core.exception.ExceptionCode;
import org.omgcms.core.model.User;
import org.omgcms.core.model.UserRole;
import org.omgcms.core.service.UserRoleService;
import org.omgcms.core.service.UserService;
import org.omgcms.web.constant.MessageKeys;
import org.omgcms.web.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
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

    @GetMapping("/users")
    public Object getUserList(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        Page<User> usersPage = userService.findAll(pageNo, pageSize, "screenName", true);

        return usersPage;

    }

    /**
     * 创建用户
     *
     * @param screenName  账号
     * @param userName    姓名
     * @param email       邮箱地址
     * @param jobTitle    职务
     * @param age         年龄
     * @param birthday    生日
     * @param description 描述
     * @param address     地址
     * @param sex         性别
     * @return
     */
    @PostMapping("/user")
    public Object createUser(@RequestParam(value = "screenName") String screenName,
                             @RequestParam(value = "userName") String userName,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "jobTitle", required = false) String jobTitle,
                             @RequestParam(value = "age", defaultValue = "0", required = false) Integer age,
                             @RequestParam(value = "birthday", required = false, defaultValue = "0") Long birthday,
                             @RequestParam(value = "description", required = false) String description,
                             @RequestParam(value = "address", required = false) String address,
                             @RequestParam(value = "sex", required = false, defaultValue = "0") String sex) {

        User user = new User();

        user.setAge(age);
        user.setScreenName(screenName);
        user.setUserName(userName);
        user.setJobTitle(jobTitle);
        user.setEmail(email);
        user.setDescription(description);
        user.setAddress(address);
        user.setSex(sex);
        user.setBirthday(new Date(birthday));

        //Set Default Password
        String defPasswd = env.getProperty("cms.system.default.password", "123456");
        user.setPassword(bCryptPasswordEncoder.encode(defPasswd));

        Date now = new Date();

        logger.debug("Now:{} Date:{} birthday:{}", now.getTime(), user.getBirthday(), birthday);

        user.setCreateDate(now);
        user.setModifyDate(now);


        User savedUser = userService.save(user);

        return savedUser;
    }

    @PutMapping("/user")
    public Object updateUser(@RequestParam(value = "userId") Long userId,
                             @RequestParam(value = "screenName") String screenName,
                             @RequestParam(value = "userName") String userName,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "jobTitle", required = false) String jobTitle,
                             @RequestParam(value = "age", defaultValue = "0") Integer age,
                             @RequestParam(value = "birthday", required = false, defaultValue = "0") Long birthday,
                             @RequestParam(value = "description", required = false) String description,
                             @RequestParam(value = "address", required = false) String address,
                             @RequestParam(value = "sex", required = false, defaultValue = "0") String sex) {

        User user = userService.getUser(userId);

        user.setAge(age);
        user.setScreenName(screenName);
        user.setUserName(userName);
        user.setJobTitle(jobTitle);
        user.setEmail(email);
        user.setDescription(description);
        user.setAddress(address);
        user.setSex(sex);
        user.setBirthday(new Date(birthday));

        Date now = new Date();
        user.setModifyDate(now);

        User savedUser = userService.save(user);

        return savedUser;
    }


    @DeleteMapping("/user")
    public Object deleteUser(@RequestParam(defaultValue = "0") Long userId) {
        userService.delete(userId);
        return MessageUtil.getMessageMap(MessageKeys.MSG_SUCCESS);
    }

    /**
     * 为用户分配角色
     *
     * @param userId    用户ID
     * @param roleIds   角色ID
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

}
