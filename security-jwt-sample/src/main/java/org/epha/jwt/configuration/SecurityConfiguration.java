package org.epha.jwt.configuration;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.epha.jwt.security.AuthServerAccessDeniedHandler;
import org.epha.jwt.security.AuthServerSecurityContextRepository;
import org.epha.jwt.security.AuthTokenWebFilter;
import org.epha.jwt.security.jwt.AuthTokenService;
import org.epha.jwt.security.jwt.impl.AuthTokenServiceImpl;
import org.epha.jwt.security.jwt.impl.DefaultUserDetailsJwtClaimsConverterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author pangjiping
 */
@Slf4j
@EnableWebFluxSecurity
public class SecurityConfiguration {

    private final String AUTH_TOKEN_SECRET_KEY = "<YOUR_KEY>";

    private final int AUTH_TOKEN_EXPIRES_MINUTES = 60 * 10;

    private final String[] PUBLIC_ACCESS_PATHS = new String[]{
            "/webjars/springfox-swagger-ui/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/v2/api-docs/_",
            "/swagger-resources",
            "/swagger-resources/**",
            "/favicon.ico",
            "/assets/**",
            "/public/**",
            "/auth/**",
            "/"
    };

    @Bean
    public AuthTokenService authTokenService() {
        Algorithm algorithm = Algorithm.HMAC256(AUTH_TOKEN_SECRET_KEY);
        return new AuthTokenServiceImpl(algorithm, AUTH_TOKEN_EXPIRES_MINUTES);
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .securityContextRepository(new AuthServerSecurityContextRepository())
                .exceptionHandling().accessDeniedHandler(new AuthServerAccessDeniedHandler())
                .and()
                .logout().disable()
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers(PUBLIC_ACCESS_PATHS).permitAll()
                .anyExchange().authenticated()
                .and()
                .addFilterAt(
                        new AuthTokenWebFilter(
                                authTokenService(),
                                new DefaultUserDetailsJwtClaimsConverterImpl(),
                                new AuthServerSecurityContextRepository()
                        ),
                        SecurityWebFiltersOrder.AUTHENTICATION
                )
                .build();
    }

}
