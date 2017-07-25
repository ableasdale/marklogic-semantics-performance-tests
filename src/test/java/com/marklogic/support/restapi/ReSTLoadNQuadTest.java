package com.marklogic.support.restapi;

import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicReST;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("nquads")
@MarkLogicReST
@DisplayName("Benchmarking performance when loading NQuad (.nq) files using the ReST API endpoint")
class ReSTLoadNQuadTest {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load to load a 20.5MB NQuad file (1_86286.nq)")
    void testLoadingSampleOne() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(30), () -> MarkLogicReSTApiClientProvider.createPostForNQuads("nquads/1_86286.nq"));
        assertEquals(204, res.getStatus());
        assertEquals(86286, MarkLogicReSTApiClientProvider.getTripleCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 3.3MB NQuad file (2_12770.nq)")
    void testLoadingSampleTwo() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(30), () -> MarkLogicReSTApiClientProvider.createPostForNQuads("nquads/2_12770.nq"));
        assertEquals(204, res.getStatus());
        assertEquals(12770, MarkLogicReSTApiClientProvider.getTripleCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 13.9MB NQuad file (3_54187.nq)")
    void testLoadingSampleThree() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(30), () -> MarkLogicReSTApiClientProvider.createPostForNQuads("nquads/3_54187.nq"));
        assertEquals(204, res.getStatus());
        assertEquals(54187, MarkLogicReSTApiClientProvider.getTripleCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 35.3MB NQuad file (4_138495.nq)")
    void testLoadingSampleFour() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(35), () -> MarkLogicReSTApiClientProvider.createPostForNQuads("nquads/4_138495.nq"));
        assertEquals(204, res.getStatus());
        assertEquals(138495, MarkLogicReSTApiClientProvider.getTripleCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 16MB NQuad file (5_63578.nq)")
    void testLoadingSampleFive() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(30), () -> MarkLogicReSTApiClientProvider.createPostForNQuads("nquads/5_63578.nq"));
        assertEquals(204, res.getStatus());
        assertEquals(63578, MarkLogicReSTApiClientProvider.getTripleCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 31.6MB NQuad file (6_125268.nq)")
    void testLoadingSampleSix() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(35), () -> MarkLogicReSTApiClientProvider.createPostForNQuads("nquads/6_125268.nq"));
        assertEquals(204, res.getStatus());
        assertEquals(125268, MarkLogicReSTApiClientProvider.getTripleCount());
    }

}
