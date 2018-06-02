package org.omgcms.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.omgcms.core.model.pk.UserRolePK;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 23:43 2018/4/11
 * @Modified By
 */
@Table(name = "user_role")
@Entity
public class UserRole  implements Serializable {

    @EmbeddedId
    private UserRolePK id;

    /**
     * MapsId 表示userId作为外键
     * @return
     */
    @JsonIgnore
    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @JsonIgnore
    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;


    public UserRole(){

    }

    public UserRole(long userId, long roleId){
        this.id = new UserRolePK(userId, roleId);
    }

    public UserRole(User user, Role role){
        this(user.getUserId(),role.getRoleId());
        this.user = user;
        this.role = role;
    }


    public UserRolePK getId() {
        return id;
    }

    public void setId(UserRolePK id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
