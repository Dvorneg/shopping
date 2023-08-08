package ru.den.shopping.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.den.shopping.model.Role;
import ru.den.shopping.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return "ROLE_" + name();
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        for (Role role : user.getRoles()) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return list;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        //return this.user.getUsername();
        return this.user.getName();
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
        return true;
    }

    //get user authenticated user
    public User getUser() {
        return this.user;
    }
}
