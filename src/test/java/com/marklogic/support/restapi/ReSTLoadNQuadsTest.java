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
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("nquads")
@MarkLogicReST
@DisplayName("Benchmarking performance when loading N-Quads (.nq) files using the ReST API endpoint")
class ReSTLoadNQuadsTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load to load a 20.5MB N-Quads file (1_86286.nq)")
    void testLoadingSampleOne() {
        Response res = assertTimeoutPreemptively(ofSeconds(20), () -> MarkLogicReSTApiClientProvider.createPostForNQuads("nquads/1_86286.nq"));
        assertEquals(204, res.code());
        assertEquals(86286, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(12003, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 3.3MB N-Quads file (2_12770.nq)")
    void testLoadingSampleTwo() {
        Response res = assertTimeoutPreemptively(ofSeconds(10), () -> MarkLogicReSTApiClientProvider.createPostForNQuads("nquads/2_12770.nq"));
        assertEquals(204, res.code());
        assertEquals(12770, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2905, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 13.9MB N-Quads file (3_54187.nq)")
    void testLoadingSampleThree() {
        Response res = assertTimeoutPreemptively(ofSeconds(15), () -> MarkLogicReSTApiClientProvider.createPostForNQuads("nquads/3_54187.nq"));
        assertEquals(204, res.code());
        assertEquals(54187, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(12157, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 35.3MB N-Quads file (4_138495.nq)")
    void testLoadingSampleFour() {
        Response res = assertTimeoutPreemptively(ofSeconds(35), () -> MarkLogicReSTApiClientProvider.createPostForNQuads("nquads/4_138495.nq"));
        assertEquals(204, res.code());
        assertEquals(138495, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(25995, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 16MB N-Quads file (5_63578.nq)")
    void testLoadingSampleFive() {
        Response res = assertTimeoutPreemptively(ofSeconds(20), () -> MarkLogicReSTApiClientProvider.createPostForNQuads("nquads/5_63578.nq"));
        assertEquals(204, res.code());
        assertEquals(63578, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(12323, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 31.6MB N-Quads file (6_125268.nq)")
    void testLoadingSampleSix() {
        Response res = assertTimeoutPreemptively(ofSeconds(35), () -> MarkLogicReSTApiClientProvider.createPostForNQuads("nquads/6_125268.nq"));
        assertEquals(204, res.code());
        assertEquals(125268, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(24263, MarkLogicReSTApiClientProvider.getGraphCount());
    }

}
