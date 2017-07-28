package com.marklogic.support.rdf4j;

import com.marklogic.client.semantics.GraphManager;
import com.marklogic.semantics.rdf4j.MarkLogicRepositoryConnection;
import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.Utils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicRDF4J;
import com.marklogic.support.providers.MarkLogicRDF4JRepositoryProvider;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import java.io.IOException;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 28/05/2017.
 */

@Tag("turtle")
@MarkLogicRDF4J
@DisplayName("Benchmarking performance when loading Turtle (.ttl) files using the RDF4J API")
public class RDF4JLoadTurtleTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 596Kb x-turtle file (charging-stations-export-20170530-095533.ttl)")
    void testLoadingSmallXTurtleFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(2), () -> conn.add(Utils.getFileReader("turtle/charging-stations-export-20170530-095533.ttl"), GraphManager.DEFAULT_GRAPH, RDFFormat.TURTLE));
        assertEquals(8900, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 779K Turtle file (units.ttl)")
    void testLoadingSmallTurtleFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(2), () -> conn.add(Utils.getFileReader("turtle/units.ttl"), GraphManager.DEFAULT_GRAPH, RDFFormat.TURTLE));
        assertEquals(23485, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 3.3MB Turtle file (unescothes.ttl)")
    void testLoadingMediumSizeTurtleFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(10), () -> conn.add(Utils.getFileReader("turtle/unescothes.ttl"), GraphManager.DEFAULT_GRAPH, RDFFormat.TURTLE));
        assertEquals(75202, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 51MB Turtle file (history.ttl)")
    void testLoadingLargeTurtleFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(50), () -> conn.add(Utils.getFileReader("turtle/history.ttl"), GraphManager.DEFAULT_GRAPH, RDFFormat.TURTLE));
        assertEquals(391551, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 130MB Turtle file (fulldump.ttl)")
    void testLoadingAnotherLargeTurtleFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(95), () -> conn.add(Utils.getFileReader("turtle/fulldump.ttl"), GraphManager.DEFAULT_GRAPH, RDFFormat.TURTLE));
        assertEquals(204122, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}
