package org.epha.r2dbc.repo.impl;

import org.epha.r2dbc.entity.UserEntity;
import org.epha.r2dbc.exception.NotFoundException;
import org.epha.r2dbc.repo.UserRepo;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.CriteriaDefinition;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.data.relational.core.sql.Where;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

/**
 * @author pangjiping
 */
@Repository("r2dbc_template")
public class UserRepoWithR2dbcTemplateImpl implements UserRepo {

    @Resource
    R2dbcEntityTemplate r2dbcEntityTemplate;

    @Override
    public Flux<UserEntity> findAll() {
        Query query = Query.empty();
        return r2dbcEntityTemplate.select(query, UserEntity.class);
    }

    @Override
    public Mono<UserEntity> findById(UUID id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return r2dbcEntityTemplate.select(query, UserEntity.class)
                .next()
                .switchIfEmpty(Mono.error(new NotFoundException("User id \"" + id.toString() + "\"not found")));
    }

    @Override
    public Mono<UserEntity> create(UserEntity entity) {
        entity.setId(UUID.randomUUID());
        return r2dbcEntityTemplate.insert(entity);
    }

    @Override
    public Mono<UserEntity> update(UserEntity entity) {
        return findById(entity.getId())
                .flatMap(user -> {
                    return r2dbcEntityTemplate.update(entity);
                });
    }

    @Override
    public Mono<Void> deleteAll() {
        Query query = Query.empty();
        return r2dbcEntityTemplate.delete(query, UserEntity.class)
                .then();
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return findById(id)
                .flatMap(dbEntity -> {
                    Query query = Query.query(Criteria.where("id").is(id));
                    return r2dbcEntityTemplate.delete(query, UserEntity.class)
                            .then();
                });
    }
}
