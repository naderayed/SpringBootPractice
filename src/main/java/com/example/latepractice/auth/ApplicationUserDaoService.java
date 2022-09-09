package com.example.latepractice.auth;

import com.example.latepractice.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("fakeUser")
public class ApplicationUserDaoService implements ApplicationUserDao{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return applicationUsers().stream()
                .filter(applicationUser -> username.equals(applicationUser
                        .getUsername())).
                findFirst();

    }

    private List<ApplicationUser> applicationUsers(){
        List<ApplicationUser> applicationUsers = List.of(
                new ApplicationUser(
                        ApplicationUserRole.ADMIN.simpleGrantedAuthority(),
                        passwordEncoder.encode("naderDao"),
                        "naderDao",
                        true,
                        true,
                        true,
                        true
                )
        );
        return applicationUsers;
    }
}
