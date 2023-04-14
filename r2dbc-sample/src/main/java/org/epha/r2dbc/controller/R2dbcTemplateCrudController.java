package org.epha.r2dbc.controller;

import org.epha.r2dbc.entity.UserEntity;
import org.epha.r2dbc.repo.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.swing.text.html.parser.Entity;
import java.util.UUID;

/**
 * @author pangjiping
 */
@RestController
@RequestMapping("/r2dbc/user")
public class R2dbcTemplateCrudController {

    @Resource(name = "r2dbc_template")
    UserRepo userRepo;

    @GetMapping
    public Flux<UserEntity> findAll() {
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public Mono<UserEntity> findById(@PathVariable("id") UUID id) {
        return userRepo.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserEntity> create(@RequestBody UserEntity userEntity) {
        return userRepo.create(userEntity);
    }

    @PutMapping("/{id}")
    public Mono<UserEntity> update(@PathVariable("id") UUID id,
                                   @RequestBody UserEntity userEntity) {
        return userRepo.update(userEntity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable("id") UUID id) {
        return userRepo.deleteById(id);
    }

}
