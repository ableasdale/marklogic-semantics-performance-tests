package com.marklogic.support.javaclient;

import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicJavaClient;
import com.marklogic.support.providers.MarkLogicJavaClientProvider;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static com.marklogic.client.semantics.GraphManager.DEFAULT_GRAPH;
import static com.marklogic.support.Utils.getFileHandleForN3File;
import static com.marklogic.support.Utils.getFileHandleForNTriplesFile;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("n3")
@MarkLogicJavaClient
@DisplayName("Benchmarking performance when loading Notation3 (.n3) files using the MarkLogic Java Client API")
public class JavaClientN3Test {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 39.5MB Notation 3 (N3) file (event-dump.n3)")
    void testLoadingMediumN3File() {
        assertTimeoutPreemptively(ofSeconds(200), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(DEFAULT_GRAPH, getFileHandleForN3File("n3/event-dump.n3")));
        assertEquals(682466, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to to load a 57.8MB Notation 3 (N3) file (sec.n3)")
    void testLoadingLargeN3File() {
        assertTimeoutPreemptively(ofSeconds(300), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(DEFAULT_GRAPH, getFileHandleForN3File("n3/sec.n3")));
        assertEquals(1813135, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

}