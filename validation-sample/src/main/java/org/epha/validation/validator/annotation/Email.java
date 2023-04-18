package org.epha.validation.validator.annotation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

/**
 * @author pangjiping
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Email.Validator.class)
public @interface Email {

    String message() default "Invalid Email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Component
    public static class Validator implements ConstraintValidator<Email, String> {


        private static final String REGEXP = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        @Override
        public void initialize(Email constraintAnnotation) {

        }

        @Override
        public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
            if (!StringUtils.hasText(email)) {
                return true;
            }

            return Pattern.compile(REGEXP).matcher(email).matches();
        }

        public static String getEmailRegExp() {
            return REGEXP;
        }
    }

}
