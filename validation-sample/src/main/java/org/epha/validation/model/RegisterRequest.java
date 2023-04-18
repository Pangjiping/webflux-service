package org.epha.validation.model;

import lombok.Data;
import org.epha.validation.validator.annotation.AtLeastPassword;
import org.epha.validation.validator.annotation.Email;
import org.epha.validation.validator.annotation.PasswordEqualsConfirmPassword;
import org.epha.validation.validator.annotation.PasswordNotEqualsEmail;
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
