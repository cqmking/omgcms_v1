package org.omgcms.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:51 2018/4/6
 * @Modified By
 */
@Table(name = "role_")
@Entity
public class Role implements Serializable {

    @TableGenerator(name = "ID_GENERATOR", table = "idgenerator", initialValue = 1000, allocationSize = 1,
            pkColumnName = "name", pkColumnValue = "roleId", valueColumnName = "value")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Id
    private Long roleId;

    @Column(unique = true, nullable = false)
    private String name;

    /**
     * 角色编码(例如 sysadmin->（对应）系统管理员)
     */
    @Column(unique = true, nullable = false)
    private String roleKey;

    @Column(length = 1024)
    private String description;

    private Date createDate;

    private Date modifyDate;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName ="userId")})
    private Set<User> users;

    /**
     * 单向一对多
     */
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    private Set<ResourcePermission> resourcePermissions;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<ResourcePermission> getResourcePermissions() {
        return resourcePermissions;
    }

    public void setResourcePermissions(Set<ResourcePermission> resourcePermissions) {
        this.resourcePermissions = resourcePermissions;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
