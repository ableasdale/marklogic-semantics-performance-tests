package com.marklogic.support.javaclient;

import com.marklogic.support.util.SPARQLUtils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicJavaClient;
import com.marklogic.support.providers.MarkLogicJavaClientProvider;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static com.marklogic.client.semantics.GraphManager.DEFAULT_GRAPH;
import static com.marklogic.support.util.FileUtils.getFileHandleForTrigFile;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("trig")
@MarkLogicJavaClient
@DisplayName("Benchmarking performance when loading TriG (.trig) files using the MarkLogic Java Client API")
class JavaClientTriGTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 502Kb TriG file (charging-stations-export-20170530-095530.trig)")
    void testLoadingSmallTriGFile() {
        assertTimeoutPreemptively(ofSeconds(40), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(DEFAULT_GRAPH, getFileHandleForTrigFile("trig/charging-stations-export-20170530-095530.trig")));
        assertEquals(8900, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 1.9MB TriG file (example.trig)")
    void testLoadingAnotherSmallTriGFile() {
        assertTimeoutPreemptively(ofSeconds(70), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(DEFAULT_GRAPH, getFileHandleForTrigFile("trig/example.trig")));
        assertEquals(13671, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 62.3MB TriG file (wp-monthy_all.trig)")
    void testLoadingLargeTriGFile() {
        assertTimeoutPreemptively(ofSeconds(120), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(DEFAULT_GRAPH, getFileHandleForTrigFile("trig/wp-monthy_all.trig")));
        assertEquals(668367, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}
