package org.omgcms.web.rest.controller;

import org.omgcms.core.model.User;
import org.omgcms.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
