package org.epha.web.mapper;

import org.epha.web.entity.Player;
import org.epha.web.entity.PlayerEntity;
import org.epha.web.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

/**
 * 将PlayerEntity类型与Player类型的互相映射
 *
 * @author pangjiping
 */
@Mapper
public interface PlayerMapper {

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "epochMilliToOffsetDateTime")
    Player map(PlayerEntity playerEntity);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "offsetDateTimeToEpochMilli")
    PlayerEntity map(Player player);

    @Named("offsetDateTimeToEpochMilli")
    default Long offsetDateTimeToEpochMilli(OffsetDateTime offsetDateTime) {
        try {
            return offsetDateTime.toInstant().toEpochMilli();
        } catch (Exception e) {
            return null;
        }
    }

    @Named("epochMilliToOffsetDateTime")
    default OffsetDateTime epochMilliToOffsetDateTime(Long epochMilli) {
        try {
            Instant instant = Instant.ofEpochMilli(epochMilli);
            return OffsetDateTime.ofInstant(instant, ZoneId.systemDefault());
        } catch (Exception e) {
            return null;
        }
    }

}
