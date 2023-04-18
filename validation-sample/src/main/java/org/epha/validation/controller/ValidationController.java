package org.epha.validation.controller;

import lombok.extern.slf4j.Slf4j;
import org.epha.validation.model.LoginRequest;
import org.epha.validation.model.RegisterRequest;
import org.epha.validation.validator.ManualValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author pangjiping
 */
@RestController
@Slf4j
public class ValidationController {

    @Resource
    ManualValidator manualValidator;

    /**
     * 通过@Validated注解自动触发校验
     */
    @PostMapping("/login")
    public void login(@RequestBody @Validated LoginRequest request) {
        log.debug("username => {}", request.getUsername());
        log.debug("password => {}", request.getPassword());
    }

    /**
     * 使用手动校验
     */
    @PostMapping("/manual/login")
    public void manualLogin(@RequestBody LoginRequest request) {
        manualValidator.validate(request);

        log.debug("username => {}", request.getUsername());
        log.debug("password => {}", request.getPassword());
    }

    /**
     * 自定义注解和validator
     */
    @PostMapping("/register")
    public void register(@RequestBody @Validated RegisterRequest request) {
        log.debug("email => {}", request.getEmail());
        log.debug("password => {}", request.getPassword());
    }

}
