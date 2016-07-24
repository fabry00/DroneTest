package com.mycompany.commons.health;

import com.codahale.metrics.health.HealthCheck;
import com.mycompany.service.UBConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HealthCheckTask extends HealthCheck {

    private final Logger log = LoggerFactory.getLogger(HealthCheckTask.class);
    private final UBConfiguration configuration;

    public HealthCheckTask(UBConfiguration configuration) {
        this.configuration = configuration;

    }

    @Override
    protected Result check() {
        this.log.info("checking");

        return Result.healthy();

    }
}
