package org.epha.web.exception.handler.ex;

import com.google.common.base.CaseFormat;
import org.epha.web.exception.ErrorResponse;
import org.epha.web.exception.handler.ExceptionHandlerAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 处理校验错误的handler WebExchangeBindException
 *
 * @author pangjiping
 */
@Component
public class WebExchangeBindExceptionHandler extends ExceptionHandlerAdapter<WebExchangeBindException> {

    private List<String> standardCodes = Arrays.asList("not_null", "not_blank");

    @Override
    public Class<WebExchangeBindException> getTypeClass() {
        return WebExchangeBindException.class;
    }

    @Override
    protected Mono<ErrorResponse> buildError(ServerWebExchange exchange, WebExchangeBindException e) {
        return Mono.fromCallable(() -> ErrorResponse.builder()
                .error("invalid_request")
                .errorDescription("Validate Failed")
                .errorStatus(HttpStatus.BAD_REQUEST.value())
                .errorFields(
                        e.getFieldErrors().stream()
                                .map(fieldError -> ErrorResponse.Field.builder()
                                        .name(fieldError.getField())
                                        .code(replace(fieldError.getCode()))
                                        .description(fieldError.getDefaultMessage())
                                        .build())
                                .collect(Collectors.toList())
                )
                .build());
    }

    private String replace(String code) {
        String underscoreCode = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, code);

        for (String standardCode : standardCodes) {
            if (!underscoreCode.equals(standardCode) && underscoreCode.endsWith(standardCode)) {
                int index = underscoreCode.indexOf(standardCode);
                return underscoreCode.substring(index);
            }
        }

        return underscoreCode;
    }
}
