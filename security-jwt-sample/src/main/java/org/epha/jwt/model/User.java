package org.epha.jwt.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * @author pangjiping
 */
@Data
@Builder
public class User {

    private UUID id;

    private String name;

}
