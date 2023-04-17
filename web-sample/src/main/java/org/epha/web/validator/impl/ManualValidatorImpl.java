package org.epha.web.validator.impl;

import lombok.extern.slf4j.Slf4j;
import org.epha.web.validator.ManualValidator;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.annotation.Resource;
import javax.validation.groups.Default;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pangjiping
 */
@Component
@Slf4j
public class ManualValidatorImpl implements ManualValidator {

    @Resource
    private Validator validator;

    @Override
    public void validate(Object object) {
        validate(object, null);
    }

    @Override
    public void validate(Object object, List<Class<?>> groupClasses) {
        BindException bindException = new BindException(object, "");
        WebExchangeBindException webExchangeBindException = new WebExchangeBindException(fakeMethodParameter(), bindException);
        List<Class<?>> classes = new ArrayList<>();

        if (groupClasses != null) {
            classes.addAll(groupClasses);
        }

        classes.add(Default.class);
        ValidationUtils.invokeValidator(validator, object, webExchangeBindException, classes);
        if (webExchangeBindException.hasErrors()) {
            throw webExchangeBindException;
        }
    }

    private MethodParameter fakeMethodParameter() {

        try {
            Method method = getClass().getDeclaredMethod("fakeMethodParameter");
            return new MethodParameter(method, -1);
        } catch (NoSuchMethodException e) {
            log.warn("fakeMethodParameter error => ", e);
            return null;
        }

    }
}
