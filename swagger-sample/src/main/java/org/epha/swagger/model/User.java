package org.epha.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author pangjiping
 */
@Data
@Builder
@ApiModel(description = "用户实体类")
public class User {

    @ApiModelProperty(value = "用户id", position = 0)
    private String id;

    @ApiModelProperty(value = "用户名", position = 1)
    private String username;

    @ApiModelProperty(value = "密码", position = 2)
    private String password;

    @ApiModelProperty(value = "邮箱", position = 3)
    private String email;

}
