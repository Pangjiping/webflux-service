package org.epha.error.exception.handler.ex;

import org.epha.error.exception.ErrorResponse;
import org.epha.error.exception.handler.ExceptionHandlerAdapter;
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
