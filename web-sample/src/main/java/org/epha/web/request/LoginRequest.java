package org.epha.web.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author pangjiping
 */
@Data
public class LoginRequest {

    @NotBlank(message = "用户名不能为空")
    @Length(max = 50, message = "用户名不能超过{max}字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 50, message = "密码必须大于{min}个字符，小于{max}个字符")
    private String password;

}
