package org.epha.r2dbc.controller;

import org.epha.r2dbc.entity.ProvinceEntity;
import org.epha.r2dbc.repo.ProvinceRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author pangjiping
 */
@RestController
@RequestMapping("/province")
public class PaginationController {

    @Resource
    ProvinceRepo provinceRepo;

    @GetMapping("/slice")
    public Flux<ProvinceEntity> findAllAsSlice() {
        Pageable pageable = SliceRequest.of(
                0,
                5,
                Sort.by(Sort.Order.by("name").with(Sort.Direction.ASC))
        );

        return provinceRepo.findAllAsSlice(Query.empty(), pageable);
    }

    @GetMapping("/page")
    public Mono<Page<ProvinceEntity>> findAllAsPage() {
        Pageable pageable = PageRequest.of(
                0,
                5,
                Sort.by(Sort.Order.by("name").with(Sort.Direction.ASC))
        );
        return provinceRepo.findAllAsPage(Query.empty(), pageable);
    }


    public static class SliceRequest implements Pageable {

        private final long offset;

        private final int limit;

        private final Sort sort;

        protected SliceRequest(final long offset, final int limit, final Sort sort) {
            this.offset = offset;
            this.limit = limit;
            this.sort = sort;
        }

        public static SliceRequest of(final long offset, final int limit, final Sort sort) {
            return new SliceRequest(offset, limit, sort);
        }

        public static SliceRequest of(final long offset, final int limit) {
            return of(offset, limit, Sort.unsorted());
        }

        @Override
        public int getPageNumber() {
            return -1;
        }

        @Override
        public int getPageSize() {
            return this.limit;
        }

        @Override
        public long getOffset() {
            return this.offset;
        }

        @Override
        public Sort getSort() {
            return this.sort;
        }

        @Override
        public Pageable next() {
            return null;
        }

        @Override
        public Pageable previousOrFirst() {
            return null;
        }

        @Override
        public Pageable first() {
            return null;
        }

        @Override
        public Pageable withPage(int pageNumber) {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }
    }

}
