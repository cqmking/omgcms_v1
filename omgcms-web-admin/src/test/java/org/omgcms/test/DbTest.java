package org.omgcms.test;

import org.junit.Test;
import org.omgcms.BaseTestCase;
import org.omgcms.core.repository.UserRepository;
import org.omgcms.core.model.User;
import org.omgcms.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 3:22 2018/4/6
 * @Modified By
 */
public class DbTest extends BaseTestCase {

    private static Logger _log = LoggerFactory.getLogger(DbTest.class);

    @Autowired
    private UserService userService;


    @Test
    public void testAddUser() {
        _log.debug("===========================>");
        Date now = new Date();
        User user = new User();
        user.setScreenName("luffy");
        user.setUserName("路飞");
        user.setEmail("luffy@qq.com");
        user.setAge(1);
        user.setPassword("123456");
        user.setCreateDate(now);
        user.setModifyDate(now);

        User newUser = userService.save(user);

        _log.debug(newUser.toString());

    }

}
