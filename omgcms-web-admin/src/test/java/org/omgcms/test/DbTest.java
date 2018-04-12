package org.omgcms.test;

import org.junit.Test;
import org.omgcms.BaseTestCase;
import org.omgcms.core.model.Role;
import org.omgcms.core.model.User;
import org.omgcms.core.service.RoleService;
import org.omgcms.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    public void testAddUser() {

        _log.debug("===========================>");
        Date now = new Date();
        User user = new User();
        user.setScreenName("luffy");
        user.setUserName("路飞");
        user.setEmail("luffy@qq.com");
        user.setAge(1);
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        user.setCreateDate(now);
        user.setModifyDate(now);

        User user2 = new User();
        user2.setScreenName("lich");
        user2.setUserName("李琦");
        user2.setEmail("lich@qq.com");
        user2.setAge(1);
        user2.setPassword(bCryptPasswordEncoder.encode("123456"));
        user2.setCreateDate(now);
        user2.setModifyDate(now);

        User newUser = userService.save(user);
        userService.save(user2);


        Role role = new Role();
        role.setName("系统管理员");
        role.setRoleKey("sysadmin");
        role.setCreateDate(now);
        role.setModifyDate(now);

        roleService.save(role);

        _log.debug(newUser.toString());

    }

    /*
    @Test
    public void testAddUserRole() {

        User user = userService.getUser(1002);
        Role role = roleService.getRole(1001);


        user.getRoles().add(role);

        user.setModifyDate(new Date());

        userService.save(user);

    }


    @Test
    public void testAddRoleUser() {

        User user = userService.getUser(1002);
        Role role = roleService.getRole(1001);


        role.getUsers().add(user);

        role.setModifyDate(new Date());

        roleService.save(role);

    }

    @Test
    public void deleteRoleUser() {

        Role role = roleService.getRole(1001);

        for(User user:role.getUsers()){
            role.getUsers().remove(user);
        }

        roleService.save(role);

    }

    @Test
    public void deleteUserRole() {
        User user = userService.getUser(1002);
        for (Role role:user.getRoles()){
            user.getRoles().remove(role);
        }

        userService.save(user);
    }

    @Test
    public void deleteUser() {
        userService.delete(1002);
    }

    */

}
