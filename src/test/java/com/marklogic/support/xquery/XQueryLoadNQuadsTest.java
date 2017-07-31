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

@Tag("nquads")
@MarkLogic
@DisplayName("Benchmarking performance when loading N-Quads (.nq) files using XQuery")
public class XQueryLoadNQuadsTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 20.5MB N-Quads file (1_86286.nq)")
    void testLoadingSampleOne() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(20), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("nquads/1_86286.nq", "nquad")));
        assertEquals("OK", res.getClientResponseStatus().getReasonPhrase());
        assertEquals(200, res.getStatus());
        assertEquals(86286, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(12003, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 3.3MB N-Quads file (2_12770.nq)")
    void testLoadingSampleTwo() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(10), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("nquads/2_12770.nq", "nquad")));
        assertEquals("OK", res.getClientResponseStatus().getReasonPhrase());
        assertEquals(200, res.getStatus());
        assertEquals(12770, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2905, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 13.9MB N-Quads file (3_54187.nq)")
    void testLoadingSampleThree() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(15), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("nquads/3_54187.nq", "nquad")));
        assertEquals("OK", res.getClientResponseStatus().getReasonPhrase());
        assertEquals(200, res.getStatus());
        assertEquals(54187, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(12157, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 35.3MB N-Quads file (4_138495.nq)")
    void testLoadingSampleFour() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(40), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("nquads/4_138495.nq", "nquad")));
        assertEquals("OK", res.getClientResponseStatus().getReasonPhrase());
        assertEquals(200, res.getStatus());
        assertEquals(138495, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(25995, MarkLogicReSTApiClientProvider.getGraphCount());
    }


    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 16MB N-Quads file (5_63578.nq)")
    void testLoadingSampleFive() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(20), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("nquads/5_63578.nq", "nquad")));
        assertEquals("OK", res.getClientResponseStatus().getReasonPhrase());
        assertEquals(200, res.getStatus());
        assertEquals(63578, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(12323, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 31.6MB N-Quads file (6_125268.nq)")
    void testLoadingSampleSix() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(35), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("nquads/6_125268.nq", "nquad")));
        assertEquals("OK", res.getClientResponseStatus().getReasonPhrase());
        assertEquals(200, res.getStatus());
        assertEquals(125268, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(24263, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}
