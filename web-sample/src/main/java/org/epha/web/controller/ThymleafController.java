package org.epha.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author pangjiping
 */
@Controller
@RequestMapping("/th")
public class ThymleafController {

    @Resource
    TemplateEngine templateEngine;

    @GetMapping({"", "/"})
    public Mono<String> index(Model model) {
        model.addAttribute("name", "PPP");
        return Mono.just("index");
    }

    @GetMapping("/user")
    public Mono<ResponseEntity<String>> user() {
        return Mono.fromCallable(() -> {
            Context ctx = new Context();
            ctx.setVariable("name", "PPP PPP");
            return ResponseEntity.ok()
                    .body(templateEngine.process("user", ctx));
        });
    }

}
