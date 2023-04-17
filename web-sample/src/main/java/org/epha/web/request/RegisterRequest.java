package org.epha.web.request;

import lombok.Data;
import org.epha.web.validator.annotation.AtLeastPassword;
import org.epha.web.validator.annotation.Email;
import org.epha.web.validator.annotation.PasswordEqualsConfirmPassword;
import org.epha.web.validator.annotation.PasswordNotEqualsEmail;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author pangjiping
 */
@Data
@PasswordEqualsConfirmPassword
@PasswordNotEqualsEmail
public class RegisterRequest implements PasswordNotEqualsEmail.Model, PasswordEqualsConfirmPassword.Model {

    @NotBlank(message = "Required")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Required")
    @AtLeastPassword
    @Length(min = 8, max = 50, message = "At least {min} characters")
    private String password;

    @NotBlank(message = "Required")
    @Length(min = 8, max = 50, message = "At least {min} characters")
    private String confirmPassword;

}
