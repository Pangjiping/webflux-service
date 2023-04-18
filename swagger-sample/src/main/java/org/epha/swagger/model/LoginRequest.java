package org.epha.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author pangjiping
 */
@Data
@ApiModel(description = "用户登录请求体")
public class LoginRequest {

    @NotBlank
    @ApiModelProperty(value = "用户名", required = true, position = 0)
    private String username;

    @NotBlank
    @ApiModelProperty(value = "密码", required = true, position = 1)
    private String password;

}
