package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

@Bean
    public UserDetailsService userDetailsService(){
        UserDetails normalUser= User.withUsername("akhilesh")
                .password(bCryptPasswordEncoder().encode("123"))
                .roles("NORMAL")
                .build();
        UserDetails adminUser= User.withUsername("admin")
                .password(bCryptPasswordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(normalUser, adminUser);
        return inMemoryUserDetailsManager;
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/home/admin")
//                        .hasRole("ADMIN")
//                        .requestMatchers("/home/normal")
//                        .hasRole("NORMAL")
                        .requestMatchers("/home/public").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
