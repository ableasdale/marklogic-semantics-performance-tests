package com.marklogic.support.xquery;

import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogic;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import okhttp3.Response;
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
        Response res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("turtle/charging-stations-export-20170530-095533.ttl", "turtle")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(8900, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 779K Turtle file (units.ttl)")
    void testLoadingSmallTurtleFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("turtle/units.ttl", "turtle")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(23485, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 3.3MB Turtle file (unescothes.ttl)")
    void testLoadingMediumSizeTurtleFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("turtle/unescothes.ttl", "turtle")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(75202, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }


    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 51MB Turtle file (history.ttl)")
    void testLoadingTurtleFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(40), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("turtle/history.ttl", "turtle")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(391551, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 130MB Turtle file (fulldump.ttl)")
    void testLoadingAnotherLargeTurtleFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(60), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("turtle/fulldump.ttl", "turtle")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(204122, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}
