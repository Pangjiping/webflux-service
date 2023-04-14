package org.epha.r2dbc.repo;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.epha.r2dbc.entity.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author pangjiping
 */
public interface UserRepo {

    Flux<UserEntity> findAll();

    Mono<UserEntity> findById(final UUID id);

    Mono<UserEntity> create(final UserEntity entity);

    Mono<UserEntity> update(final UserEntity entity);

    Mono<Void> deleteAll();

    Mono<Void> deleteById(final UUID id);

    default UserEntity convert(Row row, final RowMetadata metadata) {
        return UserEntity.builder()
                .id(row.get("id", UUID.class))
                .username(row.get("username", String.class))
                .firstName(row.get("first_name", String.class))
                .lastName(row.get("last_name", String.class))
                .build();
    }

}
