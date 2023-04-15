package org.epha.web.service;

import org.epha.web.entity.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 模拟数据库的用户操作接口
 *
 * @author pangjiping
 */
public interface UserService {
    /**
     * 根据id查询用户
     * @param id 主键
     * @return 指定id的用户
     */
    Mono<UserEntity> getUserById(int id);


    /**
     * 查询所有用户
     * @return 用户集合
     */
    Flux<UserEntity> getAllUser();

    /**
     * 添加一个用户
     * @param user 插入的用户
     * @return 无返回值
     */
    Mono<Void> saveUserInfo(Mono<UserEntity> user);
}
