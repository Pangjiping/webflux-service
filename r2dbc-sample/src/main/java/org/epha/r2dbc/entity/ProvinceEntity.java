package org.epha.r2dbc.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.Generated;
import java.util.UUID;

/**
 * @author pangjiping
 */
@Data
@Builder
@Table("app.province")
public class ProvinceEntity {

    //Primary Key
    @Id
    private UUID id;

    private String name;
}
