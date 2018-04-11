package org.omgcms.security.service;

import org.omgcms.core.model.User;
import org.omgcms.core.service.UserService;
import org.omgcms.security.model.CustomUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:26 2018/4/6
 * @Modified By
 */
@Service
public class CustomUserService implements UserDetailsService {

    private final static Logger _log = LoggerFactory.getLogger(CustomUserService.class);

    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        _log.debug("Start to loadUserByUsername, username:{}", username);

        User user = userService.findByScreenName(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        UserDetails userDetails = new CustomUserDetail(user);

//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
//        for(SysRole role:user.getRoles())
//        {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//            logger.info("loadUserByUsername: " + user);
//        }
//        user.setGrantedAuthorities(authorities); //用于登录时 @AuthenticationPrincipal 标签取值

        return userDetails;
    }

}
