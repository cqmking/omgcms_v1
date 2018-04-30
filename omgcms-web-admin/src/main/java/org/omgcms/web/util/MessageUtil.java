package org.omgcms.web.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Madfrog Yang
 * @Date: 2018/4/30 12:09
 * @Description:
 */
public class MessageUtil {

    /**
     * 读取国际化配置消息
     *
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
     * @param key  键
     * @param args 参数
     * @return
     */
    public static String getMessage(String key, Object... args) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        RequestContext requestContext = new RequestContext(request);

        if (args == null || args.length == 0) {
            return requestContext.getMessage(key);
        }

        return requestContext.getMessage(key, args);
    }

}
