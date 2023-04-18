package org.epha.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author pangjiping
 */
@Data
@Builder
public class LoginResponse {

    @ApiModelProperty(value = "token")
    @JsonProperty("access_token")
    private String accessToken;

    @ApiModelProperty(value = "令牌类型")
    private String type;

    @ApiModelProperty(value = "过期（秒）")
    @JsonProperty("expires_in")
    private Long expiresIn;

    //TODO
}