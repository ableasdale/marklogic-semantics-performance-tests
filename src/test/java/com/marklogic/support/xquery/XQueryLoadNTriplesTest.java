package com.marklogic.support.xquery;

import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogic;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import com.sun.jersey.api.client.ClientResponse;
import okhttp3.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static com.marklogic.support.providers.MarkLogicReSTApiClientProvider.createXQuerySemLoad;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("ntriples")
@MarkLogic
@DisplayName("Benchmarking performance when loading N-Triples (.nt) files using XQuery")
class XQueryLoadNTriplesTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 506Kb N-Triples file (ron.nt)")
    void testLoadingSmallNtFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("nt/ron.nt", "ntriple")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(3348, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load an 801Kb N-Triples file (rmn.nt)")
    void testLoadingAnotherSmallNtFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("nt/rmn.nt", "ntriple")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(5069, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load an 11.8MB N-Triples file (dbpedia60k.nt)")
    void testLoadingMediumNtFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(10), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("nt/dbpedia60k.nt", "ntriple")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(58514, MarkLogicReSTApiClientProvider.getTripleCount()); // TODO - why does this return MORE triples!?!?!?
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load an 18.2MB N-Triples file (ron-data.nt)")
    void testLoadingMedNtFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(20), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("nt/ron-data.nt", "ntriple")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(109672, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }


    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load an 18.2MB N-Triples file (rmn-data.nt)")
    void testLoadingAnotherMedNtFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(20), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("nt/rmn-data.nt", "ntriple")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(113904, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load an 96.4MB NT file (2013-02-14-panlex-dump.nt)")
    void testLoadingLargeNtFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(90), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("nt/2013-02-14-panlex-dump.nt", "ntriple")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(746399, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

}
