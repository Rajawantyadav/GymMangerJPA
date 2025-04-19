package com.gymmanager.newgymmanager.jwtconfig;

import com.gymmanager.newgymmanager.model.GymOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
   @Autowired
    private GymOwner user;

    public CustomUserDetails(GymOwner user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public String getPassword() {
        return user.getOwnerPassword();
    }

    @Override
    public String getUsername() {
        return user.getOwnerEmail();
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
        if(user.getOwnerActive().equals("1")){
            return true;
        }else {
            return false;
        }
    }
}
