package org.omgcms.security.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.List;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 2:40 2018/5/29
 * @Modified By
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SubSystemResource {

    private List<SystemResource> systemResourceList;

    @XmlElements(@XmlElement(name = "system-resource", type = SystemResource.class))
    public List<SystemResource> getSystemResourceList() {
        return systemResourceList;
    }

    public void setSystemResourceList(List<SystemResource> systemResourceList) {
        this.systemResourceList = systemResourceList;
    }
}
