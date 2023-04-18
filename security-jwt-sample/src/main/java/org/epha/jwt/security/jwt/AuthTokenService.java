package org.epha.jwt.security.jwt;

import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author pangjiping
 */
public interface AuthTokenService {

    Mono<String> sign(Map<String, Object> claims);

    Mono<Map<String, Object>> verify(String token);

}
