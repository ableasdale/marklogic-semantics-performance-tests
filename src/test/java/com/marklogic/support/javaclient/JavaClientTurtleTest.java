package com.marklogic.support.javaclient;

import com.marklogic.client.semantics.GraphManager;
import com.marklogic.support.util.SPARQLUtils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicJavaClient;
import com.marklogic.support.providers.MarkLogicJavaClientProvider;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static com.marklogic.support.util.FileUtils.getFileHandleForTurtleFile;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 29/05/2017.
 */
@Tag("turtle")
@MarkLogicJavaClient
@DisplayName("Benchmarking performance when loading Turtle (.ttl) files using the MarkLogic Java Client API")
class JavaClientTurtleTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 596Kb x-turtle file (charging-stations-export-20170530-095533.ttl)")
    void testLoadingSmallXTurtleFile() {
        assertTimeoutPreemptively(ofSeconds(70), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(GraphManager.DEFAULT_GRAPH, getFileHandleForTurtleFile("turtle/charging-stations-export-20170530-095533.ttl")));
        assertEquals(8900, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 779K Turtle file (units.ttl)")
    void testLoadingSmallTurtleFile() {
        assertTimeoutPreemptively(ofSeconds(70), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(GraphManager.DEFAULT_GRAPH, getFileHandleForTurtleFile("turtle/units.ttl")));
        assertEquals(23485, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 3.3MB Turtle file (unescothes.ttl)")
    void testLoadingMediumSizeTurtleFile() {
        assertTimeoutPreemptively(ofSeconds(10), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(GraphManager.DEFAULT_GRAPH, getFileHandleForTurtleFile("turtle/unescothes.ttl")));
        assertEquals(75202, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }


    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 51MB Turtle file (history.ttl)")
    void testLoadingLargeTurtleFile() {
        assertTimeoutPreemptively(ofSeconds(150), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(GraphManager.DEFAULT_GRAPH, getFileHandleForTurtleFile("turtle/history.ttl")));
        assertEquals(391551, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 130MB Turtle file (fulldump.ttl)")
    void testLoadingAnotherLargeTurtleFile() {
        assertTimeoutPreemptively(ofSeconds(150), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(GraphManager.DEFAULT_GRAPH, getFileHandleForTurtleFile("turtle/fulldump.ttl")));
        assertEquals(204122, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}

//
//        SPARQLQueryManager sparqlMgr = MarkLogicJavaClientProvider.getClient().newSPARQLQueryManager();
//        String sparql = "select (count(*) as ?total) where { ?s ?p ?o . }";
//        SPARQLQueryDefinition query = sparqlMgr.newQueryDefinition(sparql);
//
//        StringHandle handle = new StringHandle().withMimetype(SPARQLMimeTypes.SPARQL_CSV);
//
//        String results = sparqlMgr.executeSelect(query, handle).get();
//        System.out.println(results);
//

