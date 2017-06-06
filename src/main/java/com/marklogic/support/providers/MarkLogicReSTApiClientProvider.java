package com.marklogic.support.providers;

import com.marklogic.semantics.sesame.MarkLogicRepositoryConnection;
import com.marklogic.support.Configuration;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPDigestAuthFilter;
import org.openrdf.repository.RepositoryException;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by ableasdale on 06/06/2017.
 */
public class MarkLogicReSTApiClientProvider {

    private static URI DEFAULT_GRAPH = UriBuilder.fromUri(String.format("http://%s:%d/v1/graphs?default", Configuration.HOST, Configuration.PORT)).build();
    private static String TURTLE_MIMETYPE = "application/x-turtle";
    //, "application/x-turtle"

    public static Client getConfiguredInstance() {
        Client client = Client.create();
        client.addFilter(new HTTPDigestAuthFilter(Configuration.USERNAME, Configuration.PASSWORD));
        return client;
    }

    public static ClientResponse createPost(String filename, String mimetype) {
        WebResource wr = getConfiguredInstance().resource(DEFAULT_GRAPH);
        try {
            return wr.type(mimetype)
                    .post(ClientResponse.class, new String(Files.readAllBytes(Paths.get(Configuration.RESOURCES+filename))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ClientResponse createPostForTurtle(String filename) {
        return createPost(filename, TURTLE_MIMETYPE);
    }

    /*
    createPost
    "application/x-turtle"


        String input = new String(Files.readAllBytes(Paths.get(Configuration.RESOURCES+"turtle/history.ttl")));
        //System.out.println("Contents (Java 7) : " + contents);

        ClientResponse response = webResource.type("application/x-turtle")
                .post(ClientResponse.class, input);
        LOG.info("Status: "+ response.getStatus());
     */

}
