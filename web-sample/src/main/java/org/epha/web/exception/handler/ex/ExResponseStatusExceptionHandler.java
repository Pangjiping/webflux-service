package org.epha.web.exception.handler.ex;

import lombok.extern.slf4j.Slf4j;
import org.epha.web.exception.ErrorResponse;
import org.epha.web.exception.handler.ExceptionHandlerAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author pangjiping
 */
@Slf4j
@Component
public class ExResponseStatusExceptionHandler extends ExceptionHandlerAdapter<ResponseStatusException> {

    @Override
    public Class<ResponseStatusException> getTypeClass() {
        return ResponseStatusException.class;
    }

    @Override
    protected Mono<ErrorResponse> buildError(ServerWebExchange exchange, ResponseStatusException e) {
        return Mono.fromCallable(() -> {
            //400
            if (e.getStatus() == HttpStatus.BAD_REQUEST) {

                String message = e.getMessage();
                if (message.contains("Request body is missing")) {
                    return ErrorResponse.invalidRequest("invalid body format");
                }

                return ErrorResponse.invalidRequest(message);
            }

            //401
            if (e.getStatus() == HttpStatus.UNAUTHORIZED) {
                return ErrorResponse.unauthorized(e.getMessage());
            }

            //403
            if (e.getStatus() == HttpStatus.FORBIDDEN) {
                return ErrorResponse.accessDenied(e.getMessage());
            }

            //404
            if (e.getStatus() == HttpStatus.NOT_FOUND) {
                log.debug("Not found => {}", exchange.getRequest().getPath());
                return ErrorResponse.notFound("not found");
            }
            return ErrorResponse.serverError();
        });
    }
}
