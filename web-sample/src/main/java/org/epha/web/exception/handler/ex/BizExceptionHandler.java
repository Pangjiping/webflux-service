package org.epha.web.exception.handler.ex;

import org.epha.web.exception.BizException;
import org.epha.web.exception.ErrorResponse;
import org.epha.web.exception.handler.ExceptionHandlerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author pangjiping
 */
@Component
public class BizExceptionHandler extends ExceptionHandlerAdapter<BizException> {
    @Override
    public Class<BizException> getTypeClass() {
        return BizException.class;
    }

    @Override
    protected Mono<ErrorResponse> buildError(ServerWebExchange exchange, BizException e) {
        return Mono.fromCallable(() ->
                ErrorResponse.builder()
                        .error(e.getMessage())
                        .errorDescription(e.getDescribe())
                        .errorStatus(e.getCode())
                        .build());
    }
}
