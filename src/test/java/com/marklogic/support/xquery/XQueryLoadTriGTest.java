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

@Tag("trig")
@MarkLogic
@DisplayName("Benchmarking performance when loading TriG (.trig) files using XQuery")
class XQueryLoadTriGTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 502Kb TriG file (charging-stations-export-20170530-095530.trig)")
    void testLoadingSmallTriGFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(10), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("trig/charging-stations-export-20170530-095530.trig", "trig")));
        assertEquals("OK", res.getClientResponseStatus().getReasonPhrase());
        assertEquals(200, res.getStatus());
        assertEquals(8900, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 1.9MB TriG file (example.trig)")
    void testLoadingAnotherSmallTriGFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(20), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("trig/example.trig", "trig")));
        assertEquals("OK", res.getClientResponseStatus().getReasonPhrase());
        assertEquals(200, res.getStatus());
        assertEquals(13671, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(1807, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 62.3MB TriG file (wp-monthy_all.trig)")
    void testLoadingLargeTriGFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(400), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("trig/wp-monthy_all.trig", "trig")));
        assertEquals("OK", res.getClientResponseStatus().getReasonPhrase());
        assertEquals(200, res.getStatus());
        assertEquals(668367, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(107737, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}
