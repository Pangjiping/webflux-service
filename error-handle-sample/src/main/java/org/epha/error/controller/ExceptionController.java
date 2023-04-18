package org.epha.error.controller;

import org.epha.error.exception.BizCodeEnum;
import org.epha.error.exception.BizException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author pangjiping
 */
@RestController
public class ExceptionController {

    @GetMapping({"", "/"})
    public Mono<String> error() {
        throw new RuntimeException("Error on /");
    }

    @GetMapping("/invalidUsernamePassword")
    public Mono<String> invalidUsernamePassword() {
        throw new BizException(BizCodeEnum.INVALID_USERNAME_OR_PASSWORD);
    }

    @GetMapping("/serverError")
    public Mono<String> serverError() {
        throw new RuntimeException();
    }

}
