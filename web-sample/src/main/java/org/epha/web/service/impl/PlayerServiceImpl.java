package org.epha.web.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epha.web.entity.Player;
import org.epha.web.entity.PlayerEntity;
import org.epha.web.mapper.PlayerMapper;
import org.epha.web.service.PlayerService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.concurrent.Callable;

/**
 * @author pangjiping
 */
@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    @Resource
    private PlayerMapper playerMapper;

    @Override
    public Mono<Player> findById(Long id) {
        return Mono.fromCallable(() -> {
            PlayerEntity playerEntity = PlayerEntity.builder()
                    .id(id)
                    .name("someone")
                    .createdAt(System.currentTimeMillis())
                    .build();
            log.debug("playEntity --> {}", playerEntity);
            return playerEntity;
        }).map(playerMapper::map);
    }

    @Override
    public Mono<Player> create(Player player) {
        return Mono.fromCallable(() -> {
            PlayerEntity playerEntity = playerMapper.map(player);
            playerEntity.setId(1L);
            playerEntity.setCreatedAt(System.currentTimeMillis());

            log.debug("playerEntity --> {}", playerEntity);
            return playerEntity;
        }).map(playerMapper::map);
    }
}
