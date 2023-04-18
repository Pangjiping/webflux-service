package org.epha.web.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author pangjiping
 */
@Component
@Slf4j
public class CleanupExpiredTokenJobRunner implements JobRunner {

    @Override
    @Scheduled(cron = "*/5 * * * * *") //every 5 seconds
    public void run() {
        log.debug("clean expired token running...." + System.currentTimeMillis());
    }
}
