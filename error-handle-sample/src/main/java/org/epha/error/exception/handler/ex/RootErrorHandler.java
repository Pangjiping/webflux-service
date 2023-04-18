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
public class RootErrorHandler extends ExceptionHandlerAdapter<Error> {

    @Override
    public Class<Error> getTypeClass() {
        return Error.class;
    }

    @Override
    protected Mono<ErrorResponse> buildError(ServerWebExchange exchange, Error e) {
        return Mono.fromCallable(ErrorResponse::serverError);
    }
}
