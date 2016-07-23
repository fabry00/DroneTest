package com.mycompany.service;

import com.id.droneapi.mock.UrbanizationMatrixFactory;
import com.mycompany.commons.health.HealthCheckTask;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.net.URISyntaxException;
import com.mycompany.service.resources.*;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

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

        enableCors(environment);

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

    private void enableCors(Environment environment) {
        // Enable CORS headers
        final FilterRegistration.Dynamic cors
                = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

}
