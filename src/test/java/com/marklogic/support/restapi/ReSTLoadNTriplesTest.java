package com.marklogic.support.restapi;

import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicReST;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import com.sun.jersey.api.client.ClientResponse;
import okhttp3.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("ntriples")
@MarkLogicReST
@DisplayName("Benchmarking performance when loading N-Triples (.nt) files using the ReST API endpoint")
class ReSTLoadNTriplesTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 506Kb N-Triples file (ron.nt)")
    void testLoadingSmallNtFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.createPostForNTriples("nt/ron.nt"));
        assertEquals("Created", res.message());
        assertEquals(201, res.code()); // returns 201 because it returns content in the response body
        assertEquals(3348, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 801Kb N-Triples file (rmn.nt)")
    void testLoadingAnotherSmallNtFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.createPostForNTriples("nt/rmn.nt"));
        assertEquals("Created", res.message());
        assertEquals(201, res.code()); // returns 201 because it returns content in the response body
        assertEquals(5069, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load an 11.8MB N-Triples file (dbpedia60k.nt)")
    void testLoadingMediumNtFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(40), () -> MarkLogicReSTApiClientProvider.createPostForNTriples("nt/dbpedia60k.nt"));
        assertEquals("Created", res.message());
        assertEquals(201, res.code()); // returns 201 because it returns content in the response body
        assertEquals(58512, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load an 18.2MB N-Triples file (ron-data.nt)")
    void testLoadingMedNtFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(60), () -> MarkLogicReSTApiClientProvider.createPostForNTriples("nt/ron-data.nt"));
        assertEquals("Created", res.message());
        assertEquals(201, res.code()); // returns 201 because it returns content in the response body
        assertEquals(109672, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 18.2MB N-Triples file (rmn-data.nt)")
    void testLoadingAnotherMedNtFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(60), () -> MarkLogicReSTApiClientProvider.createPostForNTriples("nt/rmn-data.nt"));
        assertEquals("Created", res.message());
        assertEquals(201, res.code()); // returns 201 because it returns content in the response body
        assertEquals(113904, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 96.4MB NT file (2013-02-14-panlex-dump.nt)")
    void testLoadingLargeNtFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(90), () -> MarkLogicReSTApiClientProvider.createPostForNTriples("nt/2013-02-14-panlex-dump.nt"));
        assertEquals("Created", res.message());
        assertEquals(201, res.code()); // returns 201 because it returns content in the response body
        assertEquals(746399, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}
