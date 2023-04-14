package org.epha.r2dbc.repo;

import org.epha.r2dbc.entity.ProvinceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.relational.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author pangjiping
 */
public interface ProvinceRepo {

    Flux<ProvinceEntity> findAllAsSlice(final Query query, final Pageable pageable);

    Mono<Page<ProvinceEntity>> findAllAsPage(final Query query, final Pageable pageable);
}
