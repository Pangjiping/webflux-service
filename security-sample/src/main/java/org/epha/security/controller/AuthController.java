package org.epha.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author pangjiping
 */
@Controller
public class AuthController {

    @ResponseBody
    @GetMapping({"", "/"})
    public Mono<String> hello(Authentication authentication) {
        return Mono.just("Hello => " + authentication.getName());
    }

    @GetMapping("/login")
    public Mono<String> login(){
        return Mono.just("custom-login");
    }

}
