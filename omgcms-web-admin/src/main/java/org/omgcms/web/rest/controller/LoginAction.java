package org.omgcms.web.rest.controller;

import org.omgcms.core.model.User;
import org.omgcms.security.model.CustomUserDetail;
import org.omgcms.web.controller.TestController;
import org.omgcms.web.util.SiteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 2:29 2018/4/13
 * @Modified By
 */
@RestController
public class LoginAction {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public Object login(String screenName, String password) {

        Authentication auth = SiteUtil.getAuthentication();
        if (auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)){

            UserDetails currentUser = SiteUtil.getLoginUser();

            logger.debug("User[{}] already logged in.", currentUser.getUsername());

            if (currentUser instanceof CustomUserDetail) {
                CustomUserDetail cud = (CustomUserDetail) currentUser;
                return cud.getUser();
            }else{
                return currentUser;
            }

        }

        // 这句代码会自动执行自定义的 CustomDetailService.java 类
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(screenName, password));
        if (!authentication.isAuthenticated()) {
            throw new BadCredentialsException("Unknown username or password");
        }

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        UserDetails loginUser = SiteUtil.getLoginUser();

        logger.debug("User[{}] logged in successful.", loginUser.getUsername());

        if (loginUser instanceof CustomUserDetail) {
            CustomUserDetail cud = (CustomUserDetail) loginUser;
            return cud.getUser();
        }

        return null;
    }


}
