package org.epha.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pangjiping
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private String name;
    private String gender;
    private Integer age;
}
