package org.epha.web.exception.handler;

import org.epha.web.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static java.time.LocalDateTime.now;
import static org.springframework.util.StringUtils.hasText;

/**
 * @author pangjiping
 */
public abstract class ExceptionHandlerAdapter<E extends Throwable> implements ExceptionHandler<E> {

    protected abstract Mono<ErrorResponse> buildError(ServerWebExchange exchange, E e);

    private String getErrorTraceId(ServerWebExchange exchange) {
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8)
                .toUpperCase();
    }

    private HttpStatus toHttpStatus(int statusCode) {
        return (statusCode == 0)
                ? HttpStatus.INTERNAL_SERVER_ERROR
                : HttpStatus.valueOf(statusCode);
    }

    private Mono<ErrorResponse> additional(ErrorResponse err, ServerWebExchange exchange, E e) {
        return Mono.fromCallable(() -> {
            ServerHttpRequest httpReq = exchange.getRequest();
            ServerHttpResponse httpResp = exchange.getResponse();
            err.setState(httpReq.getQueryParams().getFirst("state"));
            err.setErrorAt(now());
            if (!hasText(err.getErrorTraceId())) {
                err.setErrorTraceId(getErrorTraceId(exchange));
            }
            err.setErrorOn("0");
            err.setErrorUri(exchange.getRequest().getURI().toString());
            httpResp.setStatusCode(toHttpStatus(err.getErrorStatus()));
            return err;
        });
    }

    @Override
    public Mono<ErrorResponse> handle(ServerWebExchange exchange, E e) {
        return buildError(exchange, e)
                .flatMap(err ->
                        additional(err, exchange, e)
                );
    }
}
