package com.marklogic.support.rdf4j;

/**
 * Created by ableasdale on 26/05/2017.
 */

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

@Tag("rdfxml")
@MarkLogicRDF4J
@DisplayName("Benchmarking performance when loading Resource Description Framework (.rdf) files using the RDF4J API")
class RDF4JLoadRDFXMLTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 175KB RDF/XML file (countries.rdf)")
    public void testLoadingSmallRDFXMLFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(5), () -> {
            conn.add(Utils.getFileReader("rdfxml/countries.rdf"), GraphManager.DEFAULT_GRAPH, RDFFormat.RDFXML);
        });
        assertEquals(9330, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 189KB RDF/XML file (currencies.rdf)")
    public void testLoadingAnotherSmallRDFXMLFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(5), () -> {
            conn.add(Utils.getFileReader("rdfxml/currencies.rdf"), GraphManager.DEFAULT_GRAPH, RDFFormat.RDFXML);
        });
        assertEquals(3231, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 21.8MB RDF/XML file (peel.rdf)")
    public void testLoadingMediumRDFXMLFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(45), () -> {
            conn.add(Utils.getFileReader("rdfxml/peel.rdf"), GraphManager.DEFAULT_GRAPH, RDFFormat.RDFXML);
        });
        assertEquals(271369, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}