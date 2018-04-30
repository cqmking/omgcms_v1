package org.omgcms.web.rest.controller;

import org.omgcms.core.model.User;
import org.omgcms.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Auther: Madfrog Yang
 * @Date: 2018/4/27 21:42
 * @Description:
 */

@RestController
public class UserAction {

    private static Logger logger = LoggerFactory.getLogger(UserAction.class);

    @Autowired
    private UserService userService;

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
                             @RequestParam(defaultValue = "0", required = false) Integer age,
                             @RequestParam(value = "jobTitle", required = false, defaultValue = "0") Long birthday,
                             @RequestParam(value = "jobTitle", required = false) String description,
                             @RequestParam(value = "jobTitle", required = false) String address,
                             @RequestParam(value = "jobTitle", required = false, defaultValue = "0") String sex) {

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

        Date now = new Date();
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
                             @RequestParam(defaultValue = "0") Integer age,
                             @RequestParam(value = "jobTitle", required = false, defaultValue = "0") Long birthday,
                             @RequestParam(value = "jobTitle", required = false) String description,
                             @RequestParam(value = "jobTitle", required = false) String address,
                             @RequestParam(value = "jobTitle", required = false, defaultValue = "0") String sex) {

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

}
