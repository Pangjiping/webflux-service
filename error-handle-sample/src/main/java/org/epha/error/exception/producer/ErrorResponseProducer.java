package org.epha.error.exception.producer;

import org.epha.error.exception.ErrorResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author pangjiping
 */
public interface ErrorResponseProducer {

    Mono<Void> produce(ErrorResponse err, ServerWebExchange exchange);

}
