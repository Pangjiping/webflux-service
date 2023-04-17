package org.epha.web.validator.annotation;

import org.epha.web.validator.FieldNameModifier;
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

/**
 * @author pangjiping
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordNotEqualsEmail.Validator.class)
public @interface PasswordNotEqualsEmail {

    String message() default "Password equals Email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Component
    public static class Validator implements ConstraintValidator<PasswordNotEqualsEmail, Model> {

        private PasswordNotEqualsEmail annotation;

        @Override
        public void initialize(PasswordNotEqualsEmail constraintAnnotation) {
            this.annotation = constraintAnnotation;
        }

        @Override
        public boolean isValid(Model model, ConstraintValidatorContext constraintValidatorContext) {
            String email = model.getEmail();
            String password = model.getPassword();

            if (!(StringUtils.hasText(email) && StringUtils.hasText(password))) {
                return true;
            }
            return FieldNameModifier.of(constraintValidatorContext)
                    .withMessage(annotation.message())
                    .changeTo("password")
                    .validate(!email.equals(password));
        }
    }


    public static interface Model {

        String getEmail();

        String getPassword();

    }

}
