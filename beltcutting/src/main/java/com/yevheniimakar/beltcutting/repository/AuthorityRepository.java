package com.yevheniimakar.beltcutting.repository;

import com.yevheniimakar.beltcutting.model.KnownAuthority;
import com.yevheniimakar.beltcutting.model.user.BeltCuttingUserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public interface AuthorityRepository extends JpaRepository<BeltCuttingUserAuthority, Long> {

    Set<KnownAuthority> ADMIN_AUTHORITIES = EnumSet.of( KnownAuthority.ROLE_ADMIN);

    Stream<BeltCuttingUserAuthority> findAllByIdIn(Set<KnownAuthority> ids);

}
