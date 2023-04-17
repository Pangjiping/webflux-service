package org.epha.web.exception.resolver;
import org.epha.web.exception.handler.ExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * @author pangjiping
 */
public interface ExceptionHandlerResolver {

    Mono<ExceptionHandler> resolve(Throwable e);

}
