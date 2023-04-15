package org.epha.web.service;

import org.springframework.web.server.ServerWebExchange;

import java.util.Locale;

/**
 * @author pangjiping
 */
public interface MessageService {

    String getMessage(ServerWebExchange exchange, String propertyName, Object... params);

    String getMessage(Locale locale, String propertyName, Object... params);
}
