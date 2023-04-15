package org.epha.web.service.impl;

import org.epha.web.service.MessageService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.i18n.LocaleContextResolver;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * @author pangjiping
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    MessageSource messageSource;

    @Resource
    LocaleContextResolver localeContextResolver;

    @Override
    public String getMessage(ServerWebExchange exchange, String propertyName, Object... params) {

        LocaleContext localeContext = localeContextResolver.resolveLocaleContext(exchange);
        Locale locale = localeContext.getLocale();

        return getMessage(locale, propertyName, params);
    }

    @Override
    public String getMessage(Locale locale, String propertyName, Object... params) {
        return messageSource.getMessage(propertyName,params,locale);
    }
}
