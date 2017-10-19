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

@Tag("n3")
@MarkLogic
@DisplayName("Benchmarking performance loading Notation3 (.n3) files using XQuery")
class XQueryLoadN3Test {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 39.5MB Notation 3 (N3) file (event-dump.n3)")
    void testLoadingMediumN3File() {
        Response res = assertTimeoutPreemptively(ofSeconds(60), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("n3/event-dump.n3", "n3")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(682466, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 57.8MB Notation 3 (N3) file (sec.n3)")
    public void testLoadingLargeN3File() {
        Response res = assertTimeoutPreemptively(ofSeconds(120), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("n3/sec.n3", "n3")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(1813135, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}
