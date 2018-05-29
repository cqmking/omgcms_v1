package org.omgcms.web.listener;

import org.omgcms.security.loader.ResourceActionLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:10 2018/5/30
 * @Modified By
 */
@WebListener
public class CmsServletContextListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(CmsServletContextListener.class);

    @Autowired
    private ResourceActionLoader resourceActionLoader;

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        logger.debug("contextInitialized ... ");

        try {
            resourceActionLoader.initResourceAction();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.debug("contextDestroyed ... ");
    }
}
