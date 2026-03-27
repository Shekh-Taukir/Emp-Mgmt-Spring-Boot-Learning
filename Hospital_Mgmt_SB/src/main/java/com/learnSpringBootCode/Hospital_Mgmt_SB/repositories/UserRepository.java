package com.learnSpringBootCode.Hospital_Mgmt_SB.repositories;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.User;
import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.type.AuthProviderTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);

    Optional<User> findByProviderIdAndProviderType(String providerId, AuthProviderTypeEnum authProviderTypeEnum);
}