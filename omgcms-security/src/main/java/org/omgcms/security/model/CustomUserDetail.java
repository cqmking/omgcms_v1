package org.omgcms.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.omgcms.core.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:00 2018/4/6
 * @Modified By
 */
public class CustomUserDetail extends User implements UserDetails {

    private List<? extends GrantedAuthority> authorities;

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setGrantedAuthorities(List<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getUsername() {
        return super.getScreenName();
    }

    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
