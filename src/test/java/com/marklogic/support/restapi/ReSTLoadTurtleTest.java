package com.marklogic.support.restapi;

import com.marklogic.support.Configuration;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicReST;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPDigestAuthFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by ableasdale on 06/06/2017.
 */
@MarkLogicReST
@DisplayName("Benchmarking performance when loading Turtle (.ttl) files using the ReST API endpoints")
public class ReSTLoadTurtleTest {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    public void testGet() {

        Client client = Client.create();
        client.addFilter(new HTTPDigestAuthFilter(Configuration.USERNAME, Configuration.PASSWORD));

        WebResource webResource = client.
                resource("http://"+Configuration.HOST+":"+Configuration.PORT+"/LATEST/rest-apis");

        LOG.info("ABOUT to GET");
        ClientResponse response = webResource.type("application/json")
                .get(ClientResponse.class);


        LOG.info(response.getEntity(String.class));




    }


    @Test
    public void testLoadingTurtleFile() throws IOException {

        // This will load a turtle file using the ReST API! :)

        Client client = Client.create();
        client.addFilter(new HTTPDigestAuthFilter(Configuration.USERNAME, Configuration.PASSWORD));
        WebResource webResource = client.
                resource("http://"+Configuration.HOST+":"+Configuration.PORT+"/v1/graphs?default");

        String input = new String(Files.readAllBytes(Paths.get("src/main/resources/turtle/units.ttl")));
        //System.out.println("Contents (Java 7) : " + contents);

        ClientResponse response = webResource.type("application/x-turtle")
                .post(ClientResponse.class, input);
        LOG.info("Status: "+ response.getStatus());

        //String input = "{\"person\":{\"first\":\"John\", \"last\":\"Doe\"}}";

        // curl --anyauth --user q:q -i -X POST 'http://localhost:8000/v1/graphs?default&database=example_app_db' -d @./nquad1.nq -H "Content-type:application/n-quads"
    }


    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 596Kb x-turtle file (charging-stations-export-20170530-095533.ttl)")
    public void testLoadingSmallXTurtleFile() {


        try {

            Client client = Client.create();
            client.addFilter(new HTTPDigestAuthFilter(Configuration.USERNAME, Configuration.PASSWORD));

            // http://localhost:8003/v1/documents?uri=/docs/person.json

            WebResource webResource = client.
                    resource("http://"+Configuration.HOST+":"+Configuration.PORT+"/v1/documents?uri=/example/"+ UUID.randomUUID()+".json");

            String input = "{\"person\":{\"first\":\"John\", \"last\":\"Doe\"}}";



            LOG.info("ABOUT to PUT");

            ClientResponse response = webResource.type("application/json")
                    .put(ClientResponse.class, input);

            LOG.info("PUT has just happened");
            LOG.info("Status: "+ response.getStatus());

            if (response.getStatus() != 201) {
                LOG.info("In exception");
                LOG.info("Status: "+ response.getStatus());

                LOG.info("Reason: "+ response.getClientResponseStatus().getReasonPhrase());


                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
// http://0.0.0.0:8003/v1/documents?uri=http://marklogic.com/semantics#default-graph


        } catch (Exception e) {

            e.printStackTrace();

        }


        //assertTimeoutPreemptively(ofSeconds(10), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/turtle/charging-stations-export-20170530-095533.ttl", Lang.TURTLE));
        //assertEquals(8900, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
    }
}
