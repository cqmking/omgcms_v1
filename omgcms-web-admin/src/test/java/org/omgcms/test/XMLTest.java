package org.omgcms.test;

import org.junit.Test;
import org.omgcms.security.resource.ModelResource;
import org.omgcms.security.resource.ResourceActionBean;
import org.omgcms.security.resource.Supports;
import org.omgcms.security.resource.SystemResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.List;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 2:07 2018/5/29
 * @Modified By
 */
public class XMLTest {

    @Test
    public void myTest() {


        StringBuffer buffer = null;
        JAXBContext jaxbContext;
        try {
            //读入xml文件流
            InputStream is = new FileInputStream(new File("F:\\Develop\\GitRepository\\omgcms\\omgcms-web-admin\\src\\main\\resources\\resourceActions.xml"));
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }

            //加载映射bean类
            jaxbContext = JAXBContext.newInstance(ResourceActionBean.class);
            //创建解析
            Unmarshaller um = jaxbContext.createUnmarshaller();
            StreamSource streamSource = new StreamSource(new StringReader(buffer.toString()));
            ResourceActionBean root = (ResourceActionBean) um.unmarshal(streamSource);
//            System.out.println("1========>"+root.getModelResource().getSupports().getActionKeys());
//            System.out.println("2========>"+root.getModelResource().getSiteMemberDefaults().getActionKeys());
//
//            System.out.println("3========>"+root.getSystemResource().getSupports().getActionKeys());
//            System.out.println("4========>"+root.getSystemResource().getSubSystemResource().getSystemResourceList().size());
//            System.out.println("4========>"+root.getSystemResource().getSubSystemResource().getSystemResourceList().get(0).getResourceName());
//            System.out.println("4========>"+root.getSystemResource().getSubSystemResource().getSystemResourceList().get(1).getResourceName());
//            System.out.println("4========>"+root.getSystemResource().getSubSystemResource().getSystemResourceList().get(2).getResourceName());

            List<ModelResource> modelResourceList = root.getModelResourceList();
            for (ModelResource mr : modelResourceList) {
                String modelName = mr.getModelName();
                Supports supports = mr.getSupports();
                System.out.println(modelName + ": " + supports.getActionKeys());
            }

            List<SystemResource> systemResourceList = root.getSystemResourceList();
            for (SystemResource sr : systemResourceList) {
                String resourceName = sr.getResourceName();
                Supports supports = sr.getSupports();
                System.out.println(resourceName + ": " + supports.getActionKeys());

                List<SystemResource> systemResourceList1 = sr.getSubSystemResource().getSystemResourceList();
                if (sr.getSubSystemResource() != null && systemResourceList1.size() > 0) {
                    for (SystemResource sr1 : systemResourceList1) {
                        String resourceName1 = sr1.getResourceName();
                        Supports supports1 = sr1.getSupports();
                        System.out.println(resourceName1 + ": " + supports1.getActionKeys());
                    }
                }
            }

            System.out.println(root);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
