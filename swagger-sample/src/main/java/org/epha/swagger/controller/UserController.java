package org.epha.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.epha.swagger.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author pangjiping
 */
@Slf4j
@RestController
@RequestMapping("/users")
@Api(tags = {"User"}, description = "用户请求")
public class UserController {

    private final User ME = User.builder()
            .id(UUID.randomUUID().toString())
            .username("epha")
            .password("test")
            .email("epha@gmail.com")
            .build();

    @GetMapping
    @ApiOperation(value = "获取所有用户")
    public Flux<User> findAll() {
        log.debug("find all users in database...");
        return Flux.just(
                ME,
                User.builder()
                        .id(UUID.randomUUID().toString())
                        .username("test")
                        .password("password")
                        .email("test@example.com")
                        .build()
        );
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查找用户")
    public Mono<User> findById(@PathVariable("id") String id) {
        log.debug("find data by id in database...");
        return Mono.just(ME);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "创建用户")
    public Mono<User> create(@RequestBody User user) {
        log.debug("insert data to database...");
        return Mono.just(user);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改用户信息")
    public Mono<User> update(@PathVariable("id") String id,
                             @RequestBody User user) {
        log.debug("update data in database...");
        return Mono.just(user);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除用户")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable("id") String id) {
        log.debug("delete data in database...");
        return Mono.empty();
    }

    @GetMapping("/me")
    @ApiOperation(value = "获取自己的用户数据")
    public Mono<User> getUser() {
        return Mono.just(ME);
    }

}
