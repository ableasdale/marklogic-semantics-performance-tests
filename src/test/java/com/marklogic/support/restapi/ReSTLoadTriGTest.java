package com.marklogic.support.restapi;

import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicReST;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import okhttp3.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("trig")
@MarkLogicReST
@DisplayName("Benchmarking performance when loading TriG (.trig) files using the ReST API endpoint")
class ReSTLoadTriGTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 502Kb TriG file (charging-stations-export-20170530-095530.trig)")
    void testLoadingSmallTriGFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.createPostForTrig("trig/charging-stations-export-20170530-095530.trig"));
        assertEquals(201, res.code());
        assertEquals(8900, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 1.9MB TriG file (example.trig)")
    void testLoadingAnotherSmallTriGFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(20), () -> MarkLogicReSTApiClientProvider.createPostForTrig("trig/example.trig"));
        assertEquals(201, res.code());
        assertEquals(13671, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 62.3MB TriG file (wp-monthy_all.trig)")
    void testLoadingLargeTriGFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(150), () -> MarkLogicReSTApiClientProvider.createPostForTrig("trig/wp-monthy_all.trig"));
        assertEquals(201, res.code());
        assertEquals(668367, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}