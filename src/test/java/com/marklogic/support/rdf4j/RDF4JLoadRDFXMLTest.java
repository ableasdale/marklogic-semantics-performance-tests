package com.marklogic.support.rdf4j;

/**
 * Created by ableasdale on 26/05/2017.
 */

import com.marklogic.semantics.rdf4j.MarkLogicRepositoryConnection;
import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.Utils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicRDF4J;
import com.marklogic.support.providers.MarkLogicSesameRepositoryProvider;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParseException;

import java.io.IOException;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("ignore")
@MarkLogicRDF4J
@DisplayName("Benchmarking performance when loading Resource Description Framework (.rdf) files using the Sesame Repository API")
class RDF4JLoadRDFXMLTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 175KB RDF/XML file (countries.rdf)")
    public void testLoadingSmallRDFXMLFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(2), () -> {
            conn.add(Utils.getFileReader("rdfxml/countries.rdf"), "", RDFFormat.RDFXML);
        });
        assertEquals(9330, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 189KB RDF/XML file (currencies.rdf)")
    public void testLoadingAnotherSmallRDFXMLFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(2), () -> {
            conn.add(Utils.getFileReader("rdfxml/currencies.rdf"), "", RDFFormat.RDFXML);
        });
        assertEquals(3231, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 21.8MB RDF/XML file (peel.rdf)")
    public void testLoadingMediumRDFXMLFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(45), () -> {
            conn.add(Utils.getFileReader("rdfxml/peel.rdf"), "", RDFFormat.RDFXML);
        });
        assertEquals(271369, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }


    @Benchmark
    @Disabled("file can't be parsed right now - need to figure out why...")
    // -- XDMP-BASEURI (err:FOER0000): Undeclared base URI . See the MarkLogic server error log for further detail.
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 187.1MB RDF/XML file (geospecies.rdf)")
    public void testLoadingLargeRDFXMLFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofMillis(50000), () -> {
            conn.add(Utils.getFileReader("rdfxml/geospecies.rdf"), "", RDFFormat.RDFXML);
        });
        assertEquals(0, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }
}