package org.epha.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pangjiping
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerEntity {

    private Long id;

    private String name;

    /**
     * Not same type
     */
    private Long createdAt;
}
