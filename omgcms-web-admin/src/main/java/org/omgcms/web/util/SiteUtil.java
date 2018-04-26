package org.omgcms.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

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


    /**
     * 读取国际化配置消息
     * @param key key
     * @return
     */
    public static String getMessage(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        RequestContext requestContext = new RequestContext(request);
        return requestContext.getMessage(key);
    }

    /**
     * 读取国际化配置消息
     *
     * @param key   键
     * @param args  参数
     * @return
     */
    public static String getMessage(String key, Object[] args) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        RequestContext requestContext = new RequestContext(request);
        return requestContext.getMessage(key, args);
    }

}
