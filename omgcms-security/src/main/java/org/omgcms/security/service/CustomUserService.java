package org.omgcms.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        _log.debug("Start to loadUserByUsername, username:{}", username);
        return null;
    }

}
