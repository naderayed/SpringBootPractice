package com.example.latepractice.security;


import com.example.latepractice.auth.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;



    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
     httpSecurity.csrf().disable()
                     .authorizeHttpRequests(auth-> auth.anyRequest().authenticated())
                             .formLogin()
             .loginPage("/login")
             .passwordParameter("password")
             .usernameParameter("username")
             .defaultSuccessUrl("/student/all",true)
             .permitAll()
             .and()
             .rememberMe()
             .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(12))
             .key("securedKeyHere")
             .and()
             .logout()
             .logoutUrl("/logout")
             .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
             .clearAuthentication(true)
             .invalidateHttpSession(true)
             .deleteCookies("JSESSIONID","remember-me")
             .logoutSuccessUrl("/login");

    return  httpSecurity.build();
    }

    protected void Configure(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(applicationUserService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }



//InMemoryUser
  /*  @Bean
    UserDetailsService userDetailsService(){
        UserDetails naderUser = User.builder()
                .username("nader")
                .password(passwordEncoder.encode("nader"))
                .authorities(ADMIN.simpleGrantedAuthority())
                .build();

        return new InMemoryUserDetailsManager(
                naderUser
        );
    }
    */

}
