package org.omgcms.core.model.pk;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 23:52 2018/4/11
 * @Modified By
 */
@Embeddable
public class UserRolePK implements Serializable {

    private Long userId;

    private Long roleId;

    public UserRolePK() {
    }

    public UserRolePK(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
