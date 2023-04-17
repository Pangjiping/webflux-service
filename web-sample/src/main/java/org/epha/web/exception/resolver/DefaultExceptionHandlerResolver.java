package org.epha.web.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.epha.web.exception.handler.ExceptionHandler;
import org.epha.web.exception.handler.ex.RootErrorHandler;
import org.epha.web.exception.handler.ex.RootExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * @author pangjiping
 */
@Component
@Slf4j
public class DefaultExceptionHandlerResolver implements ExceptionHandlerResolver {

    private final Map<Class, ExceptionHandler> registry;

    private final RootErrorHandler rootErrorHandler;

    private final RootExceptionHandler rootExceptionHandler;

    @Autowired
    public DefaultExceptionHandlerResolver(
            List<ExceptionHandler> handlers,
            RootErrorHandler rootErrorHandler,
            RootExceptionHandler rootExceptionHandler
    ) {
        this.registry = handlers.stream()
                .filter(this::ignoreHandler)
                .collect(toMap(ExceptionHandler::getTypeClass, handler -> handler));
        this.rootErrorHandler = rootErrorHandler;
        this.rootExceptionHandler = rootExceptionHandler;
    }

    private boolean ignoreHandler(ExceptionHandler handler) {
        return !(handler.getTypeClass() == Exception.class
                || handler.getTypeClass() == Error.class);
    }

    @Override
    public Mono<ExceptionHandler> resolve(Throwable e) {
        ExceptionHandler handler = registry.get(e.getClass());
        if (handler == null) {
            if (e instanceof Error) {
                handler = rootErrorHandler;
            } else {
                handler = rootExceptionHandler;
            }
        }
        log.debug("handler => {}", handler.getClass().getName());
        return Mono.just(handler);
    }
}
