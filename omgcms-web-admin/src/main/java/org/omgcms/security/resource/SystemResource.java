package org.omgcms.security.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 1:16 2018/5/29
 * @Modified By
 */
@XmlRootElement(name = "system-resource")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SystemResource {

    private String resourceName;

    private Supports supports;

    private SiteMemberDefaults siteMemberDefaults;

    private GuestDefaults guestDefaults;

    private SubSystemResource subSystemResource;

    @XmlElement(name = "resource-name")
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @XmlElement(name = "supports")
    public Supports getSupports() {
        return supports;
    }

    public void setSupports(Supports supports) {
        this.supports = supports;
    }

    @XmlElement(name = "site-member-defaults")
    public SiteMemberDefaults getSiteMemberDefaults() {
        return siteMemberDefaults;
    }

    public void setSiteMemberDefaults(SiteMemberDefaults siteMemberDefaults) {
        this.siteMemberDefaults = siteMemberDefaults;
    }

    @XmlElement(name = "guest-defaults")
    public GuestDefaults getGuestDefaults() {
        return guestDefaults;
    }

    public void setGuestDefaults(GuestDefaults guestDefaults) {
        this.guestDefaults = guestDefaults;
    }

    @XmlElement(name = "sub-resource")
    public SubSystemResource getSubSystemResource() {
        return subSystemResource;
    }

    public void setSubSystemResource(SubSystemResource subSystemResource) {
        this.subSystemResource = subSystemResource;
    }

    @Override
    public String toString() {
        return "SystemResource{" +
                "resourceName='" + resourceName + '\'' +
                ", supports=" + supports +
                ", siteMemberDefaults=" + siteMemberDefaults +
                ", guestDefaults=" + guestDefaults +
                ", subResourceList=" + subSystemResource +
                '}';
    }
}
