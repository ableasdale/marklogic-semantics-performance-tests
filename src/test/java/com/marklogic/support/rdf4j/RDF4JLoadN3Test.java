package com.marklogic.support.rdf4j;

import com.marklogic.client.semantics.GraphManager;
import com.marklogic.semantics.rdf4j.MarkLogicRepositoryConnection;
import com.marklogic.support.util.FileUtils;
import com.marklogic.support.util.SPARQLUtils;
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
 * Created by ableasdale on 26/05/2017.
 */

@Tag("n3")
@MarkLogicRDF4J
@DisplayName("Benchmarking performance when loading Notation3 (.n3) files using the RDF4J API")
class RDF4JLoadN3Test {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 39.5MB Notation 3 (N3) file (event-dump.n3)")
    void testLoadingMediumN3File() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(50), () -> {
            conn.add(FileUtils.getFileReader("n3/event-dump.n3"), GraphManager.DEFAULT_GRAPH, RDFFormat.N3);
        });
        assertEquals(682466, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 57.8MB Notation 3 (N3) file (sec.n3)")
    void testLoadingLargeN3File() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(130), () -> {
            conn.add(FileUtils.getFileReader("n3/sec.n3"), GraphManager.DEFAULT_GRAPH, RDFFormat.N3);
        });
        assertEquals(1813135, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

}
