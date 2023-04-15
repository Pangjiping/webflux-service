package org.epha.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

/**
 * @author pangjiping
 */
@RestController
@RequestMapping("/session")
@Slf4j
public class SessionController {

    @GetMapping({"", "/"})
    public Mono<WebSession> getSession(WebSession webSession) {
        return Mono.just(webSession);
    }

    @GetMapping("/create")
    public Mono<String> createSession(WebSession webSession) {
        webSession.start();
        return Mono.just("create session --> " + webSession.getId());
    }

    @GetMapping("/invalidate")
    public Mono<String> invalidateSession(WebSession webSession) {
        return webSession.invalidate()
                .then(Mono.just("invalidate session --> " + webSession.getId()));
    }
}
