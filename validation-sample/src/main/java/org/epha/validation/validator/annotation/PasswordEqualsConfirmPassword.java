package org.epha.validation.validator.annotation;

import org.epha.validation.validator.FieldNameModifier;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author pangjiping
 */
@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordEqualsConfirmPassword.Validator.class)
public @interface PasswordEqualsConfirmPassword {

    String message() default "Password not equals Confirm password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Component
    public static class Validator implements ConstraintValidator<PasswordEqualsConfirmPassword, Model> {

        private PasswordEqualsConfirmPassword annotation;

        @Override
        public void initialize(PasswordEqualsConfirmPassword constraintAnnotation) {
            this.annotation = constraintAnnotation;
        }

        @Override
        public boolean isValid(Model model, ConstraintValidatorContext constraintValidatorContext) {
            String password = model.getPassword();
            String confirmPassword = model.getConfirmPassword();

            if (password == null) {
                password = "";
            }

            if (confirmPassword == null) {
                confirmPassword = "";
            }

            return FieldNameModifier.of(constraintValidatorContext)
                    .withMessage(annotation.message())
                    .changeTo("confirmPassword")
                    .validate(password.equals(confirmPassword));
        }
    }

    public static interface Model {

        String getPassword();

        String getConfirmPassword();

    }
}
