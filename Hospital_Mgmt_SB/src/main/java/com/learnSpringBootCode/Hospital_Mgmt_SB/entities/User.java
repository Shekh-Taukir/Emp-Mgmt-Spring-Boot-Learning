package com.learnSpringBootCode.Hospital_Mgmt_SB.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="user_mst_sb")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
//to make UserAuthentication to use User entity, we need to inherit this class from UserDetails class
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
