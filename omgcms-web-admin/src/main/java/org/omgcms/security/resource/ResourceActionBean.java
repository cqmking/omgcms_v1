package org.omgcms.security.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:56 2018/5/29
 * @Modified By
 */
@XmlRootElement(name = "resource-action-mapping")
@XmlAccessorType(XmlAccessType.NONE)
public class ResourceActionBean {
    
    /**
     * 资源类型
     */
    @XmlElement(name = "model-resource")
    private List<ModelResource> modelResourceList;

    /**
     * 资源名称
     */
    @XmlElement(name = "system-resource")
    private List<SystemResource> systemResourceList;

    public List<ModelResource> getModelResourceList() {
        return modelResourceList;
    }

    public void setModelResourceList(List<ModelResource> modelResourceList) {
        this.modelResourceList = modelResourceList;
    }

    public List<SystemResource> getSystemResourceList() {
        return systemResourceList;
    }

    public void setSystemResourceList(List<SystemResource> systemResourceList) {
        this.systemResourceList = systemResourceList;
    }

    @Override
    public String toString() {
        return "ResourceActionBean{" +
                "modelResourceList=" + modelResourceList +
                ", systemResourceList=" + systemResourceList +
                '}';
    }
}
