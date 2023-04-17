package org.epha.web.exception.handler;

import org.epha.web.exception.ErrorResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author pangjiping
 */
public interface ExceptionHandler<E extends Throwable> {

    Class<E> getTypeClass();

    Mono<ErrorResponse> handle(ServerWebExchange exchange, E e);

}
