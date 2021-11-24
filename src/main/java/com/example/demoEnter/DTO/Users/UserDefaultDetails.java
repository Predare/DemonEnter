package com.example.demoEnter.DTO.Users;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demoEnter.Security.Role;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDefaultDetails implements UserDetails {

    private Integer id;
    private String username;
    private String password;

    private Boolean enabled;

    private Set<Role> authorities;

    public static Set<Role> convertAuthoritiesFromStringToRole(String stringAuthorities){
        Set<String> stringRoles = Stream.of(stringAuthorities.split(","))
            .map (elem -> new String(elem))
            .collect(Collectors.toSet());
    
        Set<Role> roles = stringRoles.stream().map(elem -> new Role(elem)).collect(Collectors.toSet());
        
        return roles;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
}
