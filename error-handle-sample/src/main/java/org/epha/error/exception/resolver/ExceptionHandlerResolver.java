package org.epha.error.exception.resolver;
import org.epha.error.exception.handler.ExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * @author pangjiping
 */
public interface ExceptionHandlerResolver {

    Mono<ExceptionHandler> resolve(Throwable e);

}
