package com.marklogic.support.rdf4j;

import com.marklogic.semantics.rdf4j.MarkLogicRepositoryConnection;
import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.Utils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicRDF4J;
import com.marklogic.support.providers.MarkLogicRDF4JRepositoryProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParseException;

import java.io.IOException;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("ntriples")
@MarkLogicRDF4J
@DisplayName("Benchmarking performance when loading N-Triples (.nt) files using the RDF4J API")
class RDF4JLoadNTriplesTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 506Kb N-Triples file (ron.nt)")
    public void testLoadingSmallNtFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofSeconds(2), () -> {
            conn.add(Utils.getFileReader("nt/ron.nt"), "", RDFFormat.NTRIPLES);
        });
        assertEquals(3348, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 801Kb N-Triples file (rmn.nt)")
    public void testLoadingAnotherSmallNtFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(2), () -> {
            conn.add(Utils.getFileReader("nt/rmn.nt"), "", RDFFormat.NTRIPLES);
        });
        assertEquals(5069, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load an 11.8MB N-Triples file (dbpedia60k.nt)")
    public void testLoadingMediumNtFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(10), () -> {
            conn.add(Utils.getFileReader("nt/dbpedia60k.nt"), "", RDFFormat.NTRIPLES);
        });
        assertEquals(58512, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load an 18.2MB N-Triples file (ron-data.nt)")
    public void testLoadingMedNtFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(10), () -> {
            conn.add(Utils.getFileReader("nt/ron-data.nt"), "", RDFFormat.NTRIPLES);
        });
        assertEquals(109672, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 18.2MB N-Triples file (rmn-data.nt)")
    public void testLoadingAnotherMedNtFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(10), () -> {
            conn.add(Utils.getFileReader("nt/rmn-data.nt"), "", RDFFormat.NTRIPLES);
        });
        assertEquals(113904, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 96.4MB N-Triples file (2013-02-14-panlex-dump.nt)")
    public void testLoadingLargeNtFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(50), () -> {
            conn.add(Utils.getFileReader("nt/2013-02-14-panlex-dump.nt"), "", RDFFormat.NTRIPLES);
        });
        assertEquals(746399, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

}

