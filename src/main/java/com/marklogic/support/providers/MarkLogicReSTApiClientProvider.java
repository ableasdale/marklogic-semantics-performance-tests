package com.marklogic.support.providers;

import com.marklogic.support.Configuration;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPDigestAuthFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by ableasdale on 06/06/2017.
 */
public class MarkLogicReSTApiClientProvider {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static URI DEFAULT_GRAPH = UriBuilder.fromUri(String.format("http://%s:%d/v1/graphs?default&database=%s", Configuration.HOST, Configuration.PORT, Configuration.DATABASE)).build();
    private static URI UNSPECIFIED_GRAPH = UriBuilder.fromUri(String.format("http://%s:%d/v1/graphs?database=%s", Configuration.HOST, Configuration.PORT, Configuration.DATABASE)).build();
    private static URI LATEST_REST_APIS = UriBuilder.fromUri(String.format("http://%s:%d/LATEST/rest-apis", Configuration.HOST, 8002)).build();

    private static String TURTLE_MIMETYPE = "application/x-turtle";
    private static String NQUAD_MIMETYPE = "application/n-quads";

    private static Client client = null;

    public static Client getConfiguredInstance() {
        if (client == null) {
            client = Client.create();
            client.addFilter(new HTTPDigestAuthFilter(Configuration.USERNAME, Configuration.PASSWORD));
        }
        return client;
    }

    public static ClientResponse createPostForClearingDatabase() {
        WebResource wr = getConfiguredInstance().resource(String.format("http://localhost:8002/manage/v2/forests/%s", Configuration.FOREST));
        ClientResponse response = wr.type("application/x-www-form-urlencoded").post(ClientResponse.class, "state=clear");
        LOG.info("Cleared Database :: Client Response Status: "+ response.getStatus());
        return response;
    }

    public static ClientResponse createGetForValidationCheck() {
        WebResource wr = getConfiguredInstance().resource(LATEST_REST_APIS);
        ClientResponse response = wr.type("application/json").get(ClientResponse.class);
        LOG.info("Client Response Status: "+ response.getStatus());
        return response;
    }

    public static ClientResponse createPost(String filename, String mimetype) {
        WebResource wr = getConfiguredInstance().resource(UNSPECIFIED_GRAPH);
        LOG.info(String.format("URI: %s", wr.getURI()));
        try {
            ClientResponse response = wr.type(mimetype)
                    .post(ClientResponse.class, new String(Files.readAllBytes(Paths.get(String.format("%s%s", Configuration.RESOURCES, filename)))));
            LOG.info("Client Response Status: "+response.getStatus());
            return response;
        } catch (IOException e) {
            LOG.error("Exception caught creating resource: ", e);
        }
        return null;
    }

    public static ClientResponse createPostForTurtle(String filename) {
        return createPost(filename, TURTLE_MIMETYPE);
    }

    public static ClientResponse createPostForNQuads(String filename) {
        return createPost(filename, NQUAD_MIMETYPE);
    }

    /*

    $ curl --anyauth --user user:password -X POST -i \
    -d "state=clear" \
    -H "Content-type: application/x-www-form-urlencoded" \
    http://localhost:8002/manage/v2/forests/rest-example-1

    http://localhost:8002/manage/v2/databases/rest-example


    createPost
    "application/x-turtle"


        String input = new String(Files.readAllBytes(Paths.get(Configuration.RESOURCES+"turtle/history.ttl")));
        //System.out.println("Contents (Java 7) : " + contents);

        ClientResponse response = webResource.type("application/x-turtle")
                .post(ClientResponse.class, input);
        LOG.info("Status: "+ response.getStatus());
     */

}
