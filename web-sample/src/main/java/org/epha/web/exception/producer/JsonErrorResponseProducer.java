package org.epha.web.exception.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.epha.web.exception.ErrorResponse;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * @author pangjiping
 */

@Component
public class JsonErrorResponseProducer implements ErrorResponseProducer {

    @Resource
    private ObjectMapper objectMapper;

    private void setHeaders(ErrorResponse err, ServerHttpResponse response) {
        HttpHeaders headers = response.getHeaders();
        response.setStatusCode(HttpStatus.valueOf(err.getErrorStatus()));
        try {
            headers.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList(MediaType.APPLICATION_JSON_VALUE));
        } catch (UnsupportedOperationException e) {

        }
    }

    @Override
    public Mono<Void> produce(ErrorResponse err, ServerWebExchange exchange) {
        return Mono.defer(() -> {
            try {
                ServerHttpResponse response = exchange.getResponse();
                setHeaders(err, response);
                String json = objectMapper.writeValueAsString(err);
                DataBuffer buffer = response.bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8));
                return response.writeWith(Mono.just(buffer))
                        .doOnError(e -> DataBufferUtils.release(buffer));
            } catch (Exception e) {
                return Mono.error(e);
            }
        });
    }
}