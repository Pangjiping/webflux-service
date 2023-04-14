package org.epha.r2dbc.controller;

import org.epha.r2dbc.dao.UserDao;
import org.epha.r2dbc.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author pangjiping
 */
@RestController
@RequestMapping("/dao/user")
public class DaoCrudController {

    @Resource
    UserDao userDao;

    @GetMapping
    public Flux<UserEntity> findAll() {
        return userDao.findAll();
    }

    @GetMapping("/{id}")
    public Mono<UserEntity> findById(@PathVariable("id") UUID id) {
        return userDao.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserEntity> create(@RequestBody UserEntity userEntity) {
        userEntity.setId(UUID.randomUUID());
        return userDao.save(userEntity);
    }

    @PutMapping("/{id}/{name}")
    public Mono<UserEntity> update(@PathVariable("id") UUID id,
                                   @PathVariable("name") String name) {
        return userDao.updateNameById(name, id)
                .flatMap(integer -> userDao.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable("id") UUID id) {
        return userDao.deleteById(id);
    }
}
