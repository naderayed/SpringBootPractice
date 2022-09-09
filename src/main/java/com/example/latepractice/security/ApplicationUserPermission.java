package com.example.latepractice.security;

public enum ApplicationUserPermission {

    STUDENT_READ("student_read"),
    STUDENT_WRITE("student_write");

    private final String permissions;

    ApplicationUserPermission(String permissions) {
        this.permissions = permissions;
    }

    public String getPermissions() {
        return permissions;
    }
}
