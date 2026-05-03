package com.example.bai1.entity;


import jakarta.annotation.Nullable;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;



import java.util.Collection;



public class UserPrincipal implements UserDetails {
    User users;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal (User users, Collection<? extends GrantedAuthority> authorities) {
        this.users = users;
        this.authorities = authorities;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return users.isEnabled();
    }
}
