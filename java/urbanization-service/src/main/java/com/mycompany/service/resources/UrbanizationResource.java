package com.mycompany.service.resources;

import com.codahale.metrics.annotation.Timed;
import com.id.droneapi.IDroneAPI;
import com.id.droneapi.INeighborhoodsAlgorithm;
import com.id.droneapi.exception.NeighborhoodsAlgorithmEx;
import com.id.droneapi.impl.DroneAPI;
import com.id.droneapi.impl.algorithm.v2.NeighborhoodsAlgorithmV2;
import com.id.droneapi.mock.UrbanizationMatrixFactory;
import com.id.droneapi.mock.api.IDealistaAPI;
import com.id.droneapi.mock.api.IUrbanizationID;
import com.id.droneapi.mock.exception.DuplicatedAdjacentNode;
import com.id.droneapi.mock.exception.NodeAlreadyAdded;
import com.id.droneapi.mock.exception.NodeAlreadyAddedAsAdjacent;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.mycompany.service.IUBConfiguration;
import java.util.List;
import java.util.logging.Level;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import org.jose4j.json.internal.json_simple.JSONObject;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/" + UrbanizationResource.UB_URL)
@Produces(MediaType.APPLICATION_JSON)
public class UrbanizationResource {

    public static final String UB_URL = "ub";
    private static final String GET_NEIGHBORHOODS_URL = "neighborhoods";

    //SLF4J is provided with dropwizard
    private final Logger log = LoggerFactory.getLogger(UrbanizationResource.class);

    private UrbanizationMatrixFactory factory;

    IUBConfiguration configuration;

    public UrbanizationResource(IUBConfiguration configuration,
            UrbanizationMatrixFactory factory) {
        this.configuration = configuration;
        this.factory = factory;
    }

    @GET
    @Timed
    // /taks-list
    // /taks-list?contains=string
    @Path("/" + GET_NEIGHBORHOODS_URL)
    public JSONObject getNeighborhoods(
            @QueryParam("rows") @NotEmpty @Min(0) @Max(100) Integer rows,
            @QueryParam("columns") @NotEmpty @Min(0) @Max(100) Integer columns,
            @QueryParam("startNodeX") @NotEmpty @Min(0) @Max(100) Double startNodeX,
            @QueryParam("startNodeY") @NotEmpty @Min(0) @Max(100) Double startNodeY,
            @QueryParam("range") @NotEmpty @Min(1) @Max(100) Integer range) {

        JSONObject jo = new JSONObject();
        try {

            IDealistaAPI apiID = factory.createUBMatrix(rows, columns);
            INeighborhoodsAlgorithm algorithm = new NeighborhoodsAlgorithmV2();
            IDroneAPI testDrone = new DroneAPI(apiID, algorithm);

            IUrbanizationID[][] matrix = apiID.getUrbanizationMatrix();
            List<IUrbanizationID> neighs
                    = testDrone.getNeighborhoods(startNodeX, startNodeY, range);

            JSONObject data = new JSONObject();
            data.put("matrix", matrix);
            data.put("neighborhoods", neighs);

            jo.put("data", data);
            return jo;

        } catch (NodeAlreadyAddedAsAdjacent | DuplicatedAdjacentNode | NodeAlreadyAdded ex) {
            String message = "Matrix creation error";
            log.error(message);
            throw new WebApplicationException(message, Response.Status.INTERNAL_SERVER_ERROR);
        } catch (NeighborhoodsAlgorithmEx ex) {
            String message = "Neightborhoos calculation error";
            log.error(message);
            throw new WebApplicationException(message, Response.Status.INTERNAL_SERVER_ERROR);
        }

    }

    /*private Process getTasks() throws IOException {
        try {
            return Runtime.getRuntime().exec("ps -e");
        } catch (IOException ex) {
            return Runtime.getRuntime().exec("tasklist");
        }
    }*/
}
