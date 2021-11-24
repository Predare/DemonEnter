package com.example.demoEnter.Security;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Role implements GrantedAuthority {

    public static final String USER_ADMIN = "user_admin";
    public static final String ENTRY_ADMIN = "entry_admin";
    public static final String GROUP_USERS_ADMIN = "group_users_admin";

    private String authority;

}
