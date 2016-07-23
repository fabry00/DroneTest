package com.mycompany.service;

import io.dropwizard.Configuration;

public class UBConfiguration extends Configuration implements IUBConfiguration {

    public static final String SERVICE_NAME = "UrbanizationService";
    public static final String SERVICE_DESC = "Urbanization service";
    public static final int MAX_TASK_LENGTH = 100;
}
