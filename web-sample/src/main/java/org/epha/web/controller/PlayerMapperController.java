package org.epha.web.controller;

import org.epha.web.entity.Player;
import org.epha.web.service.PlayerService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author pangjiping
 */
@RestController
@RequestMapping("/mapper")
public class PlayerMapperController {

    @Resource
    PlayerService playerService;

    @PostMapping
    public Mono<Player> create(@RequestBody final Player player) {
        return playerService.create(player);
    }

    @GetMapping("/{id}")
    public Mono<Player> findById(@PathVariable("id") final Long id) {
        return playerService.findById(id);
    }

}
