package org.omgcms.security.resource;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 1:29 2018/5/29
 * @Modified By
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Supports {


    private List<String> actionKeys;

    @XmlElements(@XmlElement(name = "action-key", type = String.class))
    public List<String> getActionKeys() {
        return actionKeys;
    }

    public void setActionKeys(List<String> actionKeys) {
        this.actionKeys = actionKeys;
    }

}
