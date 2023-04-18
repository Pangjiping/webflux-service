package org.epha.jwt.controller;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author pangjiping
 */
@RestController
@RequestMapping("/me")
public class MeController {

    @GetMapping
    public Mono<UserDetails> getUserDetails() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(authentication -> (UserDetails) authentication.getDetails());
    }

}
