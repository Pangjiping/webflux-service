package org.epha.web.service;

import org.epha.web.entity.Player;
import reactor.core.publisher.Mono;

/**
 * @author pangjiping
 */
public interface PlayerService {

    Mono<Player> findById(Long id);

    Mono<Player> create(Player player);

}
