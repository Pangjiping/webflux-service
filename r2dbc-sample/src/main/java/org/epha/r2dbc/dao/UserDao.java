package org.epha.r2dbc.dao;

import org.epha.r2dbc.entity.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * 继承ReactiveCrudRepo接口，获取所有基础的crud api
 *
 * @author pangjiping
 */
@Repository
public interface UserDao extends ReactiveCrudRepository<UserEntity, UUID> {

    @Query("UPDATE user SET username = :name WHERE id = :id")
    Mono<Integer> updateNameById(String name, UUID id);
}
