package com.yevheniimakar.beltcutting.service.impl;

import com.yevheniimakar.beltcutting.exceptions.BeltCuttingExceptions;
import com.yevheniimakar.beltcutting.model.KnownAuthority;
import com.yevheniimakar.beltcutting.model.auth.BeltCuttingUserDetails;
import com.yevheniimakar.beltcutting.model.user.BeltCuttingUser;
import com.yevheniimakar.beltcutting.model.user.BeltCuttingUserAuthority;
import com.yevheniimakar.beltcutting.model.user.request.SaveUserRequest;
import com.yevheniimakar.beltcutting.model.user.response.UserResponse;
import com.yevheniimakar.beltcutting.repository.AuthorityRepository;
import com.yevheniimakar.beltcutting.repository.UserRepository;
import com.yevheniimakar.beltcutting.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @Transactional
    public void mergeAdmins(List<SaveUserRequest> requests) {
        if (requests.isEmpty()) return;
        Map<KnownAuthority, BeltCuttingUserAuthority> authorities = getAdminAuthorities();
        for (SaveUserRequest request : requests) {
            String email = request.getEmail();
            String name = request.getName();
            BeltCuttingUser user = userRepository.findByEmail(email).orElseGet(() -> {
                BeltCuttingUser newUser = new BeltCuttingUser();
                newUser.setCreatedAt(OffsetDateTime.now());
                newUser.setEmail(email);
                newUser.setName(name);
                return newUser;
            });

            user.setPassword(passwordEncoder.encode(request.getPassword()));

            user.getAuthorities().putAll(authorities);
            userRepository.save(user);
        }
    }

    @Override
    public UserResponse create(SaveUserRequest request) {
        validateUniqueFields(request);
        return new UserResponse(save(request, getRegularUserAuthorities(request.getAuthorities())));
    }

    private Map<KnownAuthority, BeltCuttingUserAuthority> getAdminAuthorities( ) {
        return authorityRepository.findAllByIdIn(AuthorityRepository.ADMIN_AUTHORITIES)
                .collect(Collectors.toMap(
                        BeltCuttingUserAuthority::getId,
                        Function.identity(),
                        (e1, e2) -> e2,
                        () -> new EnumMap<>(KnownAuthority.class)));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        BeltCuttingUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found"));

        return new BeltCuttingUserDetails(user);
    }

    private void validateUniqueFields(SaveUserRequest request) {
        String email = request.getEmail();
        if (userRepository.existsByEmail(email)) {
            throw BeltCuttingExceptions.duplicateEmail(email);
        }

    }

    private BeltCuttingUser save(SaveUserRequest request, Map<KnownAuthority, BeltCuttingUserAuthority> authorities) {
        var user = new BeltCuttingUser();
        user.getAuthorities().putAll(authorities);
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(OffsetDateTime.now());
        userRepository.save(user);
        return user;
    }

    private Map<KnownAuthority, BeltCuttingUserAuthority> getRegularUserAuthorities(Set<KnownAuthority> knownAuthorities) {
        return authorityRepository.findAllByIdIn(knownAuthorities)
                .collect(Collectors.toMap(
                        BeltCuttingUserAuthority::getId,
                        Function.identity(),
                        (e1, e2) -> e2,
                        () -> new EnumMap<>(KnownAuthority.class)));
    }
}
