package com.learnSpringBootCode.Hospital_Mgmt_SB.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        /*
        //this coding was done, to enable basic form based signups and default sessions were maintained on users logsin by spring security
        httpSecurity
                .authorizeHttpRequests(auth->auth
                                .requestMatchers("/public/**").permitAll()
                                .requestMatchers("/admin/**").authenticated()
                                .requestMatchers("/product/**").hasAnyRole("DOCTOR", "ADMIN")
                        )
                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
         */

        //Now we have moved from default login and session manager to maintain authentication  system, to JWT authentication system that is configured and
        //written in our project
        httpSecurity
                .csrf(csrfConfig->csrfConfig.disable())

                .sessionManagement(
                        sessionConfig->
                                sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/public/**", "/auth/**").permitAll()
//                        .requestMatchers("/admin/**").authenticated()
//                        .requestMatchers("/product/**").hasAnyRole("DOCTOR", "ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }

//    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user1 = User
                .withUsername("system")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails user2 = User
                .withUsername("patient")
                .password(passwordEncoder.encode("patient"))
                .roles("PATIENT")
                .build();

        UserDetails user3 = User
                .withUsername("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
