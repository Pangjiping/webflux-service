package org.epha.web.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.i18n.LocaleContextResolver;

import java.util.Locale;

import static org.springframework.util.StringUtils.hasText;

/**
 * @author pangjiping
 */
@Configuration
public class I18nConfiguration {

    @Bean
    public MessageSource messageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("i18n/message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    @Primary
    public LocaleContextResolver customLocaleContextResolver() {
        return new CustomLocaleContextResolver();
    }

    public static class CustomLocaleContextResolver implements LocaleContextResolver {

        private static final String LANGUAGE_PARAMETER_NAME = "lang";

        private Locale getRequestLocale(final ServerWebExchange exchange) {
            final ServerHttpRequest request = exchange.getRequest();
            final String lang = request.getQueryParams().getFirst(LANGUAGE_PARAMETER_NAME);
            if (hasText(lang)) {
                return Locale.forLanguageTag(lang);
            }

            return request.getHeaders()
                    .getAcceptLanguageAsLocales()
                    .stream()
                    .findFirst()
                    .orElse(Locale.getDefault());
        }

        @Override
        public LocaleContext resolveLocaleContext(final ServerWebExchange exchange) {
            return new SimpleLocaleContext(getRequestLocale(exchange));
        }

        @Override
        public void setLocaleContext(final ServerWebExchange exchange, final LocaleContext localeContext) {
            //Unused
        }
    }


}
