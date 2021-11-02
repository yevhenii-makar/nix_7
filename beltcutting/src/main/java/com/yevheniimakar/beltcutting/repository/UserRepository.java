package com.yevheniimakar.beltcutting.repository;

import com.yevheniimakar.beltcutting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
