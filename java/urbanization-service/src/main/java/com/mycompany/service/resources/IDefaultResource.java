package com.mycompany.service.resources;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.service.api.IServiceInfo;
import javax.ws.rs.GET;

public interface IDefaultResource {
    
    @GET
    @Timed
    public IServiceInfo serviceInfo();
    
}
