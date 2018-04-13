package org.omgcms.core.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:57 2018/4/6
 * @Modified By
 */
@Table(name = "resourcepermission", uniqueConstraints = {
        @UniqueConstraint(name = "ResourcePermission_Unique", columnNames = {"primaryKey", "resourceName", "roleId"})})
@Entity
public class ResourcePermission implements Serializable {

    @TableGenerator(name = "ID_GENERATOR", table = "idgenerator", initialValue = 1000, allocationSize = 1,
            pkColumnName = "name", pkColumnValue = "resourcePermissionId", valueColumnName = "value")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Id
    private Long resourcePermissionId;

    /**
     * Resource's PrimaryKey. Resource entry's primary ID, common/system value is 0.
     */
    private Long primaryKey;

    @Column(length = 150, nullable = false)
    private String resourceName;

    private Integer scope;

    /**
     * The actionId's values, for wise value. (2, 4, 8, 16 etc.)
     */
    private Long actionIds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

    public Long getResourcePermissionId() {
        return resourcePermissionId;
    }

    public void setResourcePermissionId(Long resourcePermissionId) {
        this.resourcePermissionId = resourcePermissionId;
    }

    public Long getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Long primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public Long getActionIds() {
        return actionIds;
    }

    public void setActionIds(Long actionIds) {
        this.actionIds = actionIds;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
