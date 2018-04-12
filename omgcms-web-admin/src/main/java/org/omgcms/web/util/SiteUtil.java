package org.omgcms.web.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 2:52 2018/4/13
 * @Modified By
 */
public class SiteUtil {

    public static UserDetails getLoginUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.isAuthenticated()){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails;
        }

        return null;
    }

    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }



}
