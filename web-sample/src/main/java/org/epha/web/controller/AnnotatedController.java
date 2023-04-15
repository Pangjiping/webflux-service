package org.epha.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.epha.web.entity.UserEntity;
import org.epha.web.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 通过SpringMVC注解实现的webflux接口
 *
 * @author pangjiping
 */
@Slf4j
@RestController
@RequestMapping("/annotation")
public class AnnotatedController {

    @Resource
    UserService userService;

    @GetMapping("/user/{id}")
    public Mono<UserEntity> getUserId(@PathVariable("id") int id) {
        log.debug("call getUserId method");
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public Flux<UserEntity> getUsers() {
        return userService.getAllUser();
    }

    @PostMapping("/user")
    public Mono<Void> saveUser(@RequestBody UserEntity user) {
        Mono<UserEntity> userMono = Mono.just(user);
        return userService.saveUserInfo(userMono);
    }

}
