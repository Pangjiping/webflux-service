package org.epha.web.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author pangjiping
 */
@Component
@Slf4j
public class CleanupExpiredSessionJonRunner implements JobRunner {

    @Override
    @Scheduled(fixedDelay = 1000)
    public void run() {
        log.debug("clean expired session running...." + System.currentTimeMillis());
    }
}
