package org.epha.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.epha.web.configuration.properties.PppProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author pangjiping
 */
@RestController
@Slf4j
@RequestMapping("/properties")
public class PropertiesController {

    @Resource
    PppProperties pppProperties;

    @GetMapping({"", "/"})
    public Mono<PppProperties> hello() {
        return Mono.just(pppProperties);
    }

}
