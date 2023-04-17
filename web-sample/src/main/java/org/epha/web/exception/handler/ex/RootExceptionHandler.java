package org.epha.web.exception.handler.ex;

import org.epha.web.exception.ErrorResponse;
import org.epha.web.exception.handler.ExceptionHandlerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author pangjiping
 */
@Component
public class RootExceptionHandler extends ExceptionHandlerAdapter<Exception> {

    @Override
    public Class<Exception> getTypeClass() {
        return Exception.class;
    }

    @Override
    protected Mono<ErrorResponse> buildError(ServerWebExchange exchange, Exception e) {
        return Mono.fromCallable(ErrorResponse::serverError);
    }
}
