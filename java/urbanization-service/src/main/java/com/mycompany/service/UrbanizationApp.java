package com.mycompany.service;

import com.id.droneapi.mock.UrbanizationMatrixFactory;
import com.mycompany.commons.health.HealthCheckTask;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.net.URISyntaxException;
import com.mycompany.service.resources.*;

public class UrbanizationApp extends Application<UBConfiguration> {

    public static void main(final String[] args) throws Exception {
        new UrbanizationApp().run(args);
    }

    @Override
    public String getName() {
        return UBConfiguration.SERVICE_DESC;
    }

    @Override
    public void initialize(final Bootstrap<UBConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final UBConfiguration configuration,
            final Environment environment) throws URISyntaxException {

        environment.healthChecks().register(UBConfiguration.SERVICE_NAME,
                getHealthCheck(configuration, environment));
        environment.jersey().register(getDefault());
        environment.jersey().register(getDroneResource(configuration));

    }

    private IDefaultResource getDefault() {
        IDefaultResource defaultRes = new DefaultResource.Builder()
                .withName(UBConfiguration.SERVICE_NAME)
                .withDesc(UBConfiguration.SERVICE_DESC)
                .build();

        return defaultRes;
    }

    private Object getDroneResource(final UBConfiguration configuration) {

        UrbanizationMatrixFactory factory = new UrbanizationMatrixFactory();
        UrbanizationResource resource
                = new UrbanizationResource(configuration, factory);

        return resource;
    }

    private HealthCheckTask getHealthCheck(final UBConfiguration configuration,
            final Environment environment) throws URISyntaxException {

        HealthCheckTask checker = new HealthCheckTask(configuration);
        return checker;
    }

}
