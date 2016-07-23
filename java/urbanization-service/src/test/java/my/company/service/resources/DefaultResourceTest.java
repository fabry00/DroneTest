package my.company.service.resources;

import com.mycompany.service.api.IServiceInfo;
import com.mycompany.service.api.ServiceInfo;
import io.dropwizard.testing.junit.ResourceTestRule;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Rule;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import com.mycompany.service.UBConfiguration;
import com.mycompany.service.resources.DefaultResource;

/**
 *
 * @author fabry
 */
public class DefaultResourceTest {

    private static final String URI = "http://localhost:8080";
    @Rule
    public ResourceTestRule resource = ResourceTestRule.builder()
            .addResource(new DefaultResource.Builder()
                    .withName(UBConfiguration.SERVICE_NAME)
                    .withDesc(UBConfiguration.SERVICE_DESC)
                    .build()).build();

    @Test
    public void testGetGreeting() {
        IServiceInfo expected = new ServiceInfo.Builder().
                withName(UBConfiguration.SERVICE_NAME)
                .withDesc(UBConfiguration.SERVICE_DESC)
                .build();
        //Obtain client from @Rule.
        Client client = resource.client();
        //Get WebTarget from client using URI of root resource.
        WebTarget helloTarget = client.target(URI);
        //To invoke response we use Invocation.Builder
        //and specify the media type of representation asked from resource.
        Invocation.Builder builder = helloTarget.request(MediaType.APPLICATION_JSON_TYPE);
        //Obtain response.
        Response response = builder.get();

        //Do assertions.
        assertEquals(Response.Status.OK, response.getStatusInfo());
        ServiceInfo actual = response.readEntity(ServiceInfo.class);
        assertEquals(expected, actual);

    }
}
