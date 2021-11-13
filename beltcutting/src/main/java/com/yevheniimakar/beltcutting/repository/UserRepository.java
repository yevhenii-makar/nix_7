package com.yevheniimakar.beltcutting.repository;

import com.yevheniimakar.beltcutting.model.user.BeltCuttingUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<BeltCuttingUser, Long> {

    Optional<BeltCuttingUser> findByEmail(String email);

    boolean existsByEmail(String email);

}
