package org.epha.security.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * spring security配置
 *
 * @author pangjiping
 */
@Slf4j
@EnableWebFluxSecurity // 开启Spring Security
public class SecurityConfiguration {

    /**
     * 跳转到自定义登录页 /login
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/login").permitAll()
                .anyExchange().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .requiresLogout(ServerWebExchangeMatchers.pathMatchers(HttpMethod.GET, "/logout"))
                .and()
                .build();
    }

    /**
     * 声明了一个登录服务，如果有登录，则从这个服务发送的用户名中查找用户
     * 通常从数据库中查找，如果找不到，可能会抛出错误 org.springframework.security.authentication.BadCredentialsException
     */
    @Bean
    public ReactiveUserDetailsService reactiveUserDetailsService(PasswordEncoder passwordEncoder) {
        return username -> {
            log.debug("login with username --> {}", username);
            return Mono.just(
                    User.withUsername(username)
                            .password(passwordEncoder.encode("password")) // 这个密码通常需要去数据库查找
                            .authorities(Collections.emptyList())
                            .build()
            );
        };
    }

    /**
     * 密码编码器声明为 BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
