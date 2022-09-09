package com.example.latepractice.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.latepractice.security.ApplicationUserPermission.STUDENT_READ;
import static com.example.latepractice.security.ApplicationUserPermission.STUDENT_WRITE;

public enum ApplicationUserRole {

    ADMIN(Sets.newHashSet(STUDENT_WRITE,STUDENT_READ)),
    STUDENT(Sets.newHashSet(STUDENT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> simpleGrantedAuthority(){

        Set<SimpleGrantedAuthority> permi = getPermissions().stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermissions()))
                .collect(Collectors.toSet());

        permi.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permi;

    }
}
