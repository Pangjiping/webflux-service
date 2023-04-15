package org.epha.web.controller;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.epha.web.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author pangjiping
 */
@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {

    @Resource
    MessageService messageService;

    @GetMapping({"", "/"})
    public Mono<Message> getMessage(ServerWebExchange exchange) {
        return Mono.fromCallable(() ->
                Message.builder()
                        .hello(messageService.getMessage(exchange, "hello", "ppp"))
                        .build());
    }

    @Data
    @Builder
    public static class Message {
        private String hello;
    }

}
