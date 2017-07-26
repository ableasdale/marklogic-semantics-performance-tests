package com.marklogic.support.javaclient;

import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicJavaClient;
import com.marklogic.support.providers.MarkLogicJavaClientProvider;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static com.marklogic.support.Utils.getFileHandleForNQuadsFile;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("nquads")
@MarkLogicJavaClient
@DisplayName("Benchmarking performance when loading N-Quads (.nq) files using the MarkLogic Java Client API")
class JavaClientNQuadsTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 20.5MB N-Quads file (1_86286.nq)")
    void testLoadingSampleOne() {
        assertTimeoutPreemptively(ofSeconds(30), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().mergeGraphs(getFileHandleForNQuadsFile("nquads/1_86286.nq")));
        assertEquals(86286, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(12003, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    // GraphManager.DEFAULT_GRAPH

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 3.3MB N-Quads file (2_12770.nq)")
    void testLoadingSampleTwo() {
        assertTimeoutPreemptively(ofSeconds(30), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().mergeGraphs(getFileHandleForNQuadsFile("nquads/2_12770.nq")));
        assertEquals(12770, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2905, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 13.9MB N-Quads file (3_54187.nq)")
    void testLoadingSampleThree() {
        assertTimeoutPreemptively(ofSeconds(30), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().mergeGraphs(getFileHandleForNQuadsFile("nquads/3_54187.nq")));
        assertEquals(54187, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(12157, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 35.3MB N-Quads file (4_138495.nq)")
    void testLoadingSampleFour() {
        assertTimeoutPreemptively(ofSeconds(40), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().mergeGraphs(getFileHandleForNQuadsFile("nquads/4_138495.nq")));
        assertEquals(138495, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(25995, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 16MB N-Quads file (5_63578.nq)")
    void testLoadingSampleFive() {
        assertTimeoutPreemptively(ofSeconds(30), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().mergeGraphs(getFileHandleForNQuadsFile("nquads/5_63578.nq")));
        assertEquals(63578, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(12323, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 31.6MB N-Quads file (6_125268.nq)")
    void testLoadingSampleSix() {
        assertTimeoutPreemptively(ofSeconds(40), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().mergeGraphs(getFileHandleForNQuadsFile("nquads/6_125268.nq")));
        assertEquals(125268, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(24263, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}
