/*
 * Copyright 2019-Current jittagornp.me
 */
package org.epha.r2dbc.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

/**
 * @author jitta
 */
@Data
@Builder
@Table("app.user")
public class UserEntity {

    //Primary Key
    @Id
    private UUID id;

    private String username;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;
}
