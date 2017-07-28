package com.marklogic.support.restapi;

import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicReST;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.jupiter.api.*;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 06/06/2017.
 */
@Tag("turtle")
@MarkLogicReST
@DisplayName("Benchmarking performance when loading Turtle (.ttl) files using the ReST API endpoints")
public class ReSTLoadTurtleTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 596Kb x-turtle file (charging-stations-export-20170530-095533.ttl)")
    void testLoadingSmallXTurtleFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.createPostForTurtle("turtle/charging-stations-export-20170530-095533.ttl"));
        assertEquals(201, res.getStatus());
        assertEquals(8900, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 779K Turtle file (units.ttl)")
    void testLoadingSmallTurtleFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.createPostForTurtle("turtle/units.ttl"));
        assertEquals(201, res.getStatus());
        assertEquals(23485, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 3.3MB Turtle file (unescothes.ttl)")
    void testLoadingMediumSizeTurtleFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(10), () -> MarkLogicReSTApiClientProvider.createPostForTurtle("turtle/unescothes.ttl"));
        assertEquals(201, res.getStatus());
        assertEquals(75202, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 51MB Turtle file (history.ttl)")
    void testLoadingTurtleFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(80), () -> MarkLogicReSTApiClientProvider.createPostForTurtle("turtle/history.ttl"));
        assertEquals(201, res.getStatus());
        assertEquals(391551, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 130MB Turtle file (fulldump.ttl)")
    void testLoadingAnotherLargeTurtleFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(90), () -> MarkLogicReSTApiClientProvider.createPostForTurtle("turtle/fulldump.ttl"));
        assertEquals(201, res.getStatus());
        assertEquals(204122, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}