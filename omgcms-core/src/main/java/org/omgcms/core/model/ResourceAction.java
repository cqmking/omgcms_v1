package org.omgcms.core.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:57 2018/4/6
 * @Modified By
 */
@Table(name = "resourceaction", uniqueConstraints = {
        @UniqueConstraint(name = "resourceAction_Unique", columnNames = {"resourceName", "actionId"})})
@Entity
public class ResourceAction implements Serializable {

    @TableGenerator(name = "ID_GENERATOR", table = "idgenerator", initialValue = 1000, allocationSize = 1,
            pkColumnName = "name", pkColumnValue = "resourceActionId", valueColumnName = "value")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Id
    private Long resourceActionId;

    @Column(length = 150, nullable = false)
    private String resourceName;

    @Column(nullable = false)
    private String actionId;

    @Column(nullable = false)
    private Long bitwiseValue;

    public Long getResourceActionId() {
        return resourceActionId;
    }

    public void setResourceActionId(Long resourceActionId) {
        this.resourceActionId = resourceActionId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public Long getBitwiseValue() {
        return bitwiseValue;
    }

    public void setBitwiseValue(Long bitwiseValue) {
        this.bitwiseValue = bitwiseValue;
    }

}
