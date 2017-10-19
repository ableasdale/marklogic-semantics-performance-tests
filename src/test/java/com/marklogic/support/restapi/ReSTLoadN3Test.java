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

@Tag("n3")
@MarkLogicReST
@DisplayName("Benchmarking performance when loading Notation3 (.n3) files using the ReST API endpoint")
class ReSTLoadN3Test {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 39.5MB Notation 3 (N3) file (event-dump.n3)")
    void testLoadingMediumN3File() {
        Response res = assertTimeoutPreemptively(ofSeconds(150), () -> MarkLogicReSTApiClientProvider.createPostForN3("n3/event-dump.n3"));
        assertEquals("Created", res.message());
        assertEquals(201, res.code()); // returns 201 because it returns content in the response body
        assertEquals(682466, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to to load a 57.8MB Notation 3 (N3) file (sec.n3)")
    void testLoadingLargeN3File() {
        Response res = assertTimeoutPreemptively(ofSeconds(200), () -> MarkLogicReSTApiClientProvider.createPostForN3("n3/sec.n3"));
        assertEquals("Created", res.message());
        assertEquals(201, res.code()); // returns 201 because it returns content in the response body
        assertEquals(1813135, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}
