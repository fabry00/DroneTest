package com.mycompany.service.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.mycompany.service.IUBConfiguration;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class UrbanizationResource {

    IUBConfiguration configuration;

    public UrbanizationResource(IUBConfiguration configuration) {
        this.configuration = configuration;
    }
}
