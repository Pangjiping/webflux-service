package org.epha.r2dbc.repo.impl;

import org.epha.r2dbc.entity.UserEntity;
import org.epha.r2dbc.exception.NotFoundException;
import org.epha.r2dbc.repo.UserRepo;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author pangjiping
 */
@Repository("native_sql")
public class UserRepoWithNativeSqlImpl implements UserRepo {

    @Resource
    DatabaseClient databaseClient;

    @Override
    public Flux<UserEntity> findAll() {
        return databaseClient.sql("SELECT * FROM app.user")
                .map(this::convert)
                .all();
    }

    @Override
    public Mono<UserEntity> findById(final UUID id) {
        return databaseClient.sql("SELECT * FROM app.user WHERE id = :id")
                .bind("id", id)
                .map(this::convert)
                .one()
                .switchIfEmpty(Mono.error(new NotFoundException("User id \"" + id.toString() + "\"not found")));
    }

    @Override
    public Mono<UserEntity> create(final UserEntity entity) {
        entity.setId(UUID.randomUUID());

        return databaseClient.sql("INSERT INTO app.user (id, username, first_name, last_name) " +
                "VALUES (:id, :username, :first_name, :last_name)")
                .bind("id", entity.getId())
                .bind("username", entity.getUsername().getClass())
                .bind("first_name", entity.getFirstName())
                .bind("last_name", entity.getLastName())
                .then()
                .thenReturn(entity);
    }

    @Override
    public Mono<UserEntity> update(final UserEntity entity) {
        return findById(entity.getId())
                .flatMap(dbEntity ->
                        databaseClient.sql("UPDATE app.user " +
                                "SET username = :username, first_name = :first_name, last_name = :last_name " +
                                "WHERE id = :id")
                                .bind("id", entity.getId())
                                .bind("username", entity.getUsername())
                                .bind("first_name", entity.getFirstName())
                                .bind("last_name", entity.getLastName())
                                .then()
                                .thenReturn(entity)
                );
    }

    @Override
    public Mono<Void> deleteAll() {
        return databaseClient.sql("DELETE FROM app.user")
                .then();
    }

    @Override
    public Mono<Void> deleteById(final UUID id) {
        return findById(id).flatMap(dbEntity ->
                databaseClient.sql("DELETE FROM app.user WHERE id = :id")
                        .bind("id", id)
                        .then()
        );
    }
}
