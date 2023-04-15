package org.epha.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author pangjiping
 */
@RestController
@Slf4j
@RequestMapping("/cookie")
public class CookieController {

    @GetMapping({"", "/",})
    public Mono<String> getCookie(@CookieValue(value = "access_token", defaultValue = "") String accessToken) {
        return Mono.just("cookie value => " + accessToken);
    }

    @GetMapping("/create")
    public Mono<ResponseCookie> createCookie(ServerWebExchange exchange) {
        String accessToken = UUID.randomUUID().toString();
        ResponseCookie cookie = ResponseCookie.from("access_token", accessToken).build();
        exchange.getResponse().addCookie(cookie);
        return Mono.just(cookie);
    }

    @GetMapping("/invalidate")
    public Mono<String> invalidateCookie(@CookieValue(value = "access_token", defaultValue = "") String accessToken,
                                         ServerWebExchange exchange) {
        ResponseCookie cookie = ResponseCookie.from("access_token", "").maxAge(0).build();
        exchange.getResponse().addCookie(cookie);
        return Mono.just("invalidate cookie => " + accessToken);
    }
}
