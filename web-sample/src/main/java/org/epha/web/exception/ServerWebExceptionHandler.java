package org.epha.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.epha.web.exception.producer.ErrorResponseProducer;
import org.epha.web.exception.resolver.ExceptionHandlerResolver;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author pangjiping
 */
@Slf4j
@Component
@Order(-2)
public class ServerWebExceptionHandler implements WebExceptionHandler {

    @Resource
    private ErrorResponseProducer producer;

    @Resource
    private ExceptionHandlerResolver resolver;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable e) {
        log.warn("error => ", e);
        return resolver.resolve(e)
                .flatMap(handler -> (Mono<ErrorResponse>) handler.handle(exchange, e))
                .flatMap(err -> producer.produce(err, exchange));
    }
}
