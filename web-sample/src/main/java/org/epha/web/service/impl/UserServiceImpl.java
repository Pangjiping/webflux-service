package org.epha.web.service.impl;

import org.epha.web.entity.UserEntity;
import org.epha.web.service.UserService;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pangjiping
 */
@Repository
public class UserServiceImpl implements UserService {

    /**
     * 创建一个Map集合，来存储模拟数据
     */
    private final Map<Integer, UserEntity> users = new HashMap<>();

    public UserServiceImpl() {
        this.users.put(1, new UserEntity("Micah", "男", 22));
        this.users.put(2, new UserEntity("Maruko", "女", 19));
    }

    @Override
    public Mono<UserEntity> getUserById(int id) {
        return Mono.justOrEmpty(this.users.get(id));
    }

    @Override
    public Flux<UserEntity> getAllUser() {
        return Flux.fromIterable(this.users.values());
    }

    @Override
    public Mono<Void> saveUserInfo(Mono<UserEntity> user) {
        return user.doOnNext(person -> {
            // 向map中存放值
            int id = users.size() + 1;
            users.put(id, person);
        }).thenEmpty(Mono.empty());
    }
}
