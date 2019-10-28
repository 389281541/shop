package com.rainbow.admin.module;

import com.rainbow.admin.entity.Administrator;
import com.rainbow.admin.entity.Permission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户详情
 *
 * @author lujinwei
 * @since 2019-10-28
 */
public class AdminUserDetails implements UserDetails {

    private Administrator administrator;

    private List<Permission> permissionList;


    public AdminUserDetails(Administrator administrator, List<Permission> permissionList) {
        this.administrator = administrator;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissionList.stream().filter(permission -> permission.getValue()!=null)
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return administrator.getPassword();
    }

    @Override
    public String getUsername() {
        return administrator.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return administrator.getDelStatus().equals(0);
    }
}
