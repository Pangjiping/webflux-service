package org.epha.r2dbc.repo.impl;

import org.epha.r2dbc.entity.ProvinceEntity;
import org.epha.r2dbc.repo.ProvinceRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author pangjiping
 */
@Repository
public class ProvinceRepoImpl implements ProvinceRepo {

    @Resource
    R2dbcEntityTemplate r2dbcEntityTemplate;

    @Override
    public Flux<ProvinceEntity> findAllAsSlice(Query query, Pageable pageable) {

        Query q = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .sort(pageable.getSort());

        return r2dbcEntityTemplate.select(q, ProvinceEntity.class);
    }

    @Override
    public Mono<Page<ProvinceEntity>> findAllAsPage(Query query, Pageable pageable) {
        Mono<Long> count = r2dbcEntityTemplate.count(query, ProvinceEntity.class);
        Flux<ProvinceEntity> slice = findAllAsSlice(query, pageable);

        return Mono.zip(count, slice.buffer().next().defaultIfEmpty(Collections.emptyList()))
                .map(item -> new PageImpl<>(
                        item.getT2(),
                        pageable,
                        item.getT1())
                );
    }
}
