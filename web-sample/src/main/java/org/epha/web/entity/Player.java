package org.epha.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * @author pangjiping
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private Long id;

    private String name;

    /**
     * Not same type
     */
    private OffsetDateTime createdAt;

}
