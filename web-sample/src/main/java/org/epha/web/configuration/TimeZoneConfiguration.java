package org.epha.web.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * 设置默认时区
 *
 * @author pangjiping
 */
@Slf4j
@Configuration
public class TimeZoneConfiguration {

    @PostConstruct
    public void setDefaultTimeZone(){

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));

        log.info("set Default Time Zone => {}", TimeZone.getDefault().getID());

    }
}
