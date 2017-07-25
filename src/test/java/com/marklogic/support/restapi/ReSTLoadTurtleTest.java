package com.marklogic.support.restapi;

import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicReST;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 06/06/2017.
 */
@Tag("ignore")
@MarkLogicReST
@DisplayName("Benchmarking performance when loading Turtle (.ttl) files using the ReST API endpoints")
public class ReSTLoadTurtleTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 596Kb x-turtle file (charging-stations-export-20170530-095533.ttl)")
    void testLoadingSmallXTurtleFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(2), () -> MarkLogicReSTApiClientProvider.createPostForTurtle("turtle/charging-stations-export-20170530-095533.ttl"));
        assertEquals(204, res.getStatus());
        // TODO - count triples after
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 779K Turtle file (units.ttl)")
    void testLoadingSmallTurtleFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(2), () -> MarkLogicReSTApiClientProvider.createPostForTurtle("turtle/units.ttl"));
        assertEquals(204, res.getStatus());
        // TODO - count triples after
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 3.3MB Turtle file (unescothes.ttl)")
    void testLoadingMediumSizeTurtleFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(2), () -> MarkLogicReSTApiClientProvider.createPostForTurtle("turtle/unescothes.ttl"));
        assertEquals(204, res.getStatus());
        // TODO - count triples after
    }

    @Disabled
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 51MB Turtle file (history.ttl)")
    void testLoadingTurtleFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(55), () -> MarkLogicReSTApiClientProvider.createPostForTurtle("turtle/history.ttl"));
        assertEquals(204, res.getStatus());
        // TODO - count triples after
    }

    @Disabled
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 130MB Turtle file (fulldump.ttl)")
    void testLoadingAnotherLargeTurtleFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(90), () -> MarkLogicReSTApiClientProvider.createPostForTurtle("turtle/fulldump.ttl"));
        assertEquals(204, res.getStatus());
        // TODO - count triples after
    }
}

// http://localhost:8003/v1/documents?uri=/docs/person.json
// curl --anyauth --user q:q -i -X POST 'http://localhost:8000/v1/graphs?default&database=example_app_db' -d @./nquad1.nq -H "Content-type:application/n-quads"
// http://0.0.0.0:8003/v1/documents?uri=http://marklogic.com/semantics#default-graph
//assertTimeoutPreemptively(ofSeconds(10), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/turtle/charging-stations-export-20170530-095533.ttl", Lang.TURTLE));
//assertEquals(8900, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));