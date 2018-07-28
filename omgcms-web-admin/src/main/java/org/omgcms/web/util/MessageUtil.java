package org.omgcms.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Madfrog Yang
 * @Date: 2018/4/30 12:09
 * @Description:
 */
public class MessageUtil {

    private static Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    /**
     * 读取国际化配置消息,返回Map对象，key=message
     *
     * @param key key
     * @return
     */
    public static Map<String, Object> getMessageMap(String key) {

        Map<String, Object> map = new HashMap<String, Object>();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        RequestContext requestContext = new RequestContext(request);

        String message = key;

        try {
            message = requestContext.getMessage(key);
        } catch (NoSuchMessageException ex) {
            logger.warn(ex.getMessage());
        }

        map.put("message", message);

        return map;
    }

    /**
     * 读取国际化配置消息
     *
     * @param key key
     * @return
     */
    public static String getMessage(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        RequestContext requestContext = new RequestContext(request);
        String message = key;

        try {
            message = requestContext.getMessage(key);
        } catch (NoSuchMessageException ex) {
            logger.warn(ex.getMessage());
        }

        return message;
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

        String message = key;

        if (args == null || args.length == 0) {

            try {
                message = requestContext.getMessage(key);
            } catch (NoSuchMessageException ex) {
                logger.warn(ex.getMessage());
            }
            return message;
        }

        try {
            message = requestContext.getMessage(key, args);
        } catch (NoSuchMessageException ex) {
            logger.warn(ex.getMessage());
        }

        return message;
    }

}
