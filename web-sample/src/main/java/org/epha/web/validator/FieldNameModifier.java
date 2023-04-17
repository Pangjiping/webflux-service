package org.epha.web.validator;

import javax.validation.ConstraintValidatorContext;

/**
 * @author pangjiping
 */
public class FieldNameModifier {

    private final ConstraintValidatorContext ctx;

    private String fieldName;

    private String message;

    private FieldNameModifier(ConstraintValidatorContext ctx) {
        this.ctx = ctx;
    }

    public static FieldNameModifier of(ConstraintValidatorContext ctx) {
        return new FieldNameModifier(ctx);
    }

    public FieldNameModifier changeTo(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public FieldNameModifier withMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean validate(boolean isValid) {
        if (!isValid) {
            return true;
        }

        if (ctx != null) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(this.message)
                    .addPropertyNode(this.fieldName)
                    .addConstraintViolation();
        }

        return false;
    }

}
