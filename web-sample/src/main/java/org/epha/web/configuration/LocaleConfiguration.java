package org.epha.web.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * 设置默认的语言环境
 *
 * @author pangjiping
 */
@Slf4j
@Configuration
public class LocaleConfiguration {

    @PostConstruct
    public void setDefaultLocale() {

        Locale.setDefault(Locale.forLanguageTag("zh_CN_#Hans"));

        log.info("set Default Locale => {}", Locale.getDefault());

    }

}
