package org.omgcms.security.loader;

import org.omgcms.core.model.ResourceAction;
import org.omgcms.core.service.ResourceActionService;
import org.omgcms.security.resource.ModelResource;
import org.omgcms.security.resource.ResourceActionBean;
import org.omgcms.security.resource.Supports;
import org.omgcms.security.resource.SystemResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:49 2018/5/29
 * @Modified By
 */
@Service
public class ResourceActionLoader {

    private static Logger logger = LoggerFactory.getLogger(ResourceActionLoader.class);

    private ResourceActionService resourceActionService;

    public void initResourceAction() throws IOException {

        logger.debug("Start to process resource actions from xml file ...");

        String xmlFileName = "resourceActions.xml";

        ResourceActionBean resourceActionBean = loadResourceActionFromXml(xmlFileName);

        if (resourceActionBean != null) {

            List<SystemResource> systemResourceList = resourceActionBean.getSystemResourceList();

            List<ModelResource> modelResourceList = resourceActionBean.getModelResourceList();

            processSystemResource(systemResourceList);
            processModelResource(modelResourceList);

        }

    }


    protected long getMaxBitwisvalue(List<ResourceAction> resourceActionList) {

        long maxValue = 0L;

        for (ResourceAction resourceAction : resourceActionList) {
            maxValue = resourceAction.getBitwiseValue() > maxValue ? resourceAction.getBitwiseValue() : maxValue;
        }

        return maxValue == 0 ? 1 : maxValue;
    }

    /**
     * 处理系统资源信息
     *
     * @param systemResourceList 系统资源列表
     */
    private boolean processSystemResource(List<SystemResource> systemResourceList) {

        if (CollectionUtils.isEmpty(systemResourceList)) {
            return true;
        }

        for (SystemResource systemResource : systemResourceList) {

            String resourceName = systemResource.getResourceName();
            Supports supports = systemResource.getSupports();
            List<String> actionKeys = supports.getActionKeys();

            if (!CollectionUtils.isEmpty(actionKeys)) {

                for (String actionKey : actionKeys) {

                    //ResourceActionId is the primary key
                    ResourceAction resAction = new ResourceAction();
                    resAction.setResourceName(resourceName);
                    resAction.setActionId(actionKey);
//                    resAction.setBitwiseValue();

                }

            }


        }

        return true;
    }

    /**
     * 处理模型资源信息
     *
     * @param modelResourceList Model list
     */
    private boolean processModelResource(List<ModelResource> modelResourceList) {

        if (CollectionUtils.isEmpty(modelResourceList)) {
            return true;
        }

        for (ModelResource modelResource : modelResourceList) {

        }

        return true;
    }



    private ResourceActionBean loadResourceActionFromXml(String xmlFileName) throws IOException {

        BufferedReader in = null;

        try {

            ClassLoader classLoader = ResourceActionBean.class.getClassLoader();

            //getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
            URL xmlUrl = classLoader.getResource(xmlFileName);
            String filePathName = xmlUrl.getFile();

            logger.debug("Load resource action from file:[{}]", filePathName);

            InputStream is = new FileInputStream(new File(filePathName));

            in = new BufferedReader(new InputStreamReader(is));

            StringBuffer buffer = new StringBuffer();

            String line;

            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }


            //加载映射bean类
            JAXBContext jaxbContext = JAXBContext.newInstance(ResourceActionBean.class);
            //创建解析
            Unmarshaller um = jaxbContext.createUnmarshaller();
            StreamSource streamSource = new StreamSource(new StringReader(buffer.toString()));
            ResourceActionBean root = (ResourceActionBean) um.unmarshal(streamSource);

            return root;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Load resourceAction from xml failed. {}", e.getMessage());

        } finally {
            if (in != null) {
                in.close();
            }
        }

        return null;
    }


}
