package org.omgcms.security.resource;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 1:43 2018/5/29
 * @Modified By
 */
@XmlRootElement(name = "site-member-defaults")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SiteMemberDefaults {


    private List<String> actionKeys;

    @XmlElements(@XmlElement(name = "action-key", type = String.class))
    public List<String> getActionKeys() {
        return actionKeys;
    }

    public void setActionKeys(List<String> actionKeys) {
        this.actionKeys = actionKeys;
    }

}
