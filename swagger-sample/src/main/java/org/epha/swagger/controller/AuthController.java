package org.epha.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.epha.swagger.model.LoginRequest;
import org.epha.swagger.model.LoginResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author pangjiping
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Api(tags = {"Authentication"}, description = "认证")
public class AuthController {

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Mono<LoginResponse> login(@RequestBody @Validated LoginRequest request) {
        return Mono.just(
                LoginResponse.builder()
                        .accessToken(UUID.randomUUID().toString())
                        .type("bearer")
                        .expiresIn(60 * 60L) // 1Hr
                        .build()
        );
    }

}
