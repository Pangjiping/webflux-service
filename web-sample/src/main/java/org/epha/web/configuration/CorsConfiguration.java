package org.epha.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.Arrays;

/**
 * @author pangjiping
 */
@Configuration
public class CorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        org.springframework.web.cors.CorsConfiguration corsConfiguration = new org.springframework.web.cors.CorsConfiguration();

        // 允许所有来源
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));

        corsConfiguration.setMaxAge(Duration.ofMinutes(1L));

        // 允许的请求方式
        corsConfiguration.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.OPTIONS.name(),
                HttpMethod.HEAD.name()
        ));

        // 允许的请求头
        corsConfiguration.setAllowedHeaders(Arrays.asList(
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.IF_MATCH,
                HttpHeaders.IF_MODIFIED_SINCE,
                HttpHeaders.IF_NONE_MATCH,
                HttpHeaders.IF_UNMODIFIED_SINCE,
                "X-Requested-With"
        ));

        // 允许客户端读取的响应头
        corsConfiguration.setExposedHeaders(Arrays.asList(
                HttpHeaders.ETAG,
                HttpHeaders.LINK,
                "X-RateLimit-Limit",
                "X-RateLimit-Remaining",
                "X-RateLimit-Reset"
        ));

        // 设置对所有路径生效
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsWebFilter(source);
    }
}
