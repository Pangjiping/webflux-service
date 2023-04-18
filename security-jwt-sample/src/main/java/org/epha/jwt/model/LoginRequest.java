package org.epha.jwt.model;

import lombok.Data;

/**
 * @author pangjiping
 */
@Data
public class LoginRequest {

    private String username;

    private String password;

}
