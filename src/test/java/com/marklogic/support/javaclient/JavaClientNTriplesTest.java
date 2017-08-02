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
import static com.marklogic.support.Utils.getFileHandleForNTriplesFile;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("ntriples")
@MarkLogicJavaClient
@DisplayName("Benchmarking performance when loading N-Triples (.nt) files using the MarkLogic Java Client API")
class JavaClientNTriplesTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 506Kb N-Triples file (ron.nt)")
    void testLoadingSmallNtFile() {
        assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(DEFAULT_GRAPH, getFileHandleForNTriplesFile("nt/ron.nt")));
        assertEquals(3348, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 801Kb N-Triples file (rmn.nt)")
    void testLoadingAnotherSmallNtFile() {
        assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(DEFAULT_GRAPH, getFileHandleForNTriplesFile("nt/rmn.nt")));
        assertEquals(5069, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load an 11.8MB N-Triples file (dbpedia60k.nt)")
    void testLoadingMediumNtFile() {
        assertTimeoutPreemptively(ofSeconds(30), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(DEFAULT_GRAPH, getFileHandleForNTriplesFile("nt/dbpedia60k.nt")));
        assertEquals(58512, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load an 18.2MB N-Triples file (ron-data.nt)")
    void testLoadingMedNtFile() {
        assertTimeoutPreemptively(ofSeconds(60), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(DEFAULT_GRAPH, getFileHandleForNTriplesFile("nt/ron-data.nt")));
        assertEquals(109672, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 18.2MB N-Triples file (rmn-data.nt)")
    void testLoadingAnotherMedNtFile() {
        assertTimeoutPreemptively(ofSeconds(60), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(DEFAULT_GRAPH, getFileHandleForNTriplesFile("nt/rmn-data.nt")));
        assertEquals(113904, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 96.4MB NT file (2013-02-14-panlex-dump.nt)")
    void testLoadingLargeNtFile() {
        assertTimeoutPreemptively(ofSeconds(90), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(DEFAULT_GRAPH, getFileHandleForNTriplesFile("nt/2013-02-14-panlex-dump.nt")));
        assertEquals(746399, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}
