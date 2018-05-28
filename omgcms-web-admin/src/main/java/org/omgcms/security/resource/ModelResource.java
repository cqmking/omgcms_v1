package org.omgcms.security.resource;

import javax.xml.bind.annotation.*;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 1:16 2018/5/29
 * @Modified By
 */
@XmlRootElement(name = "model-resource")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ModelResource {

    private String modelName;

    private Supports supports;

    private SiteMemberDefaults siteMemberDefaults;

    private GuestDefaults guestDefaults;

    @XmlElement(name = "model-name")
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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

    @Override
    public String toString() {
        return "ModelResource{" +
                "modelName='" + modelName + '\'' +
                ", supports=" + supports +
                ", siteMemberDefaults=" + siteMemberDefaults +
                ", guestDefaults=" + guestDefaults +
                '}';
    }
}
