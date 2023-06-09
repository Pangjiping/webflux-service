package org.ehpa.authority.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author pangjiping
 */
@RestController
public class AuthController {

    @GetMapping({"", "/"})
    public Mono<String> hello(Authentication authentication) {
        return Mono.just("Hello --> " + (authentication == null ?
                "Anonymous User" : authentication.getName()));
    }

    @GetMapping("/users/authorities")
    public Flux<GrantedAuthority> getUserAuthorities() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getAuthorities)
                .flatMapMany(Flux::fromIterable);
    }

    @GetMapping("/users")
    public Mono<String> getAllUsers() {
        return Mono.just("Can get all users.");
    }

    @PostMapping("/users")
    public Mono<String> createUser() {
        return Mono.just("Can create user.");
    }

    @PutMapping("/users/{id}")
    public Mono<String> updateUser() {
        return Mono.just("Can update user.");
    }

    @DeleteMapping("/users/{id}")
    public Mono<String> deleteUser() {
        return Mono.just("Can delete user.");
    }

    @DeleteMapping("/users")
    public Mono<String> deleteAllUsers() {
        return Mono.just("Can delete all users.");
    }

    @GetMapping("/users/{id}/reset-password")
    public Mono<String> resetPassword() {
        return Mono.just("Can reset user password.");
    }

}
