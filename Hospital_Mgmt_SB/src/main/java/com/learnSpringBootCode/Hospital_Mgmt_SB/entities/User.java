package com.learnSpringBootCode.Hospital_Mgmt_SB.entities;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.type.AuthProviderTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(
        name="user_mst_sb",
        indexes = {
                @Index(name="idx_providerId_providerType_user_mst_sb",columnList = "providerId, providerType")
        }
)
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

    private String providerId;

    @Enumerated(value = EnumType.STRING)
    private AuthProviderTypeEnum providerType;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
