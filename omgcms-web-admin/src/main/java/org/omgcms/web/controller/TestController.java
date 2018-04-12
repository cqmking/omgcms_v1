package org.omgcms.web.controller;

import org.omgcms.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    private static Logger _log = LoggerFactory.getLogger(TestController.class);

    @Resource
    private UserService userService;


    @RequestMapping(value = "test")
    public Object test() {

        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("name", "刘峰");
        userMap.put("page", 18);


        _log.debug("=============>{}", userService);

        return userMap;
    }

}
