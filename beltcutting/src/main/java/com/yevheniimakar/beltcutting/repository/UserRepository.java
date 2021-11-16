package com.yevheniimakar.beltcutting.repository;

import com.yevheniimakar.beltcutting.model.UserStatus;
import com.yevheniimakar.beltcutting.model.user.BeltCuttingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<BeltCuttingUser, Long> {

    Optional<BeltCuttingUser> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("update BeltCuttingUser u set u.status = :status where u.email = :email")
    @Modifying
    void changeStatusByEmail(String email, UserStatus status);

}
