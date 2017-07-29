package com.marklogic.support.xquery;

import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogic;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static com.marklogic.support.providers.MarkLogicReSTApiClientProvider.createXQuerySemLoad;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("turtle")
@MarkLogic
@DisplayName("Benchmarking performance when loading Turtle (.ttl) files using XQuery")
class XQueryLoadTurtleTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 596Kb x-turtle file (charging-stations-export-20170530-095533.ttl)")
    void testLoadingSmallXTurtleFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("turtle/charging-stations-export-20170530-095533.ttl", "turtle")));
        assertEquals("OK", res.getClientResponseStatus().getReasonPhrase());
        assertEquals(200, res.getStatus());
        assertEquals(8900, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 779K Turtle file (units.ttl)")
    void testLoadingSmallTurtleFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("turtle/units.ttl", "turtle")));
        assertEquals("OK", res.getClientResponseStatus().getReasonPhrase());
        assertEquals(200, res.getStatus());
        assertEquals(23485, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 3.3MB Turtle file (unescothes.ttl)")
    void testLoadingMediumSizeTurtleFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("turtle/unescothes.ttl", "turtle")));
        assertEquals("OK", res.getClientResponseStatus().getReasonPhrase());
        assertEquals(200, res.getStatus());
        assertEquals(75202, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }


    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 51MB Turtle file (history.ttl)")
    void testLoadingTurtleFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(40), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("turtle/history.ttl", "turtle")));
        assertEquals("OK", res.getClientResponseStatus().getReasonPhrase());
        assertEquals(200, res.getStatus());
        assertEquals(391551, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 130MB Turtle file (fulldump.ttl)")
    void testLoadingAnotherLargeTurtleFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(120), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("turtle/fulldump.ttl", "turtle")));
        assertEquals("OK", res.getClientResponseStatus().getReasonPhrase());
        assertEquals(200, res.getStatus());
        assertEquals(204122, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}
