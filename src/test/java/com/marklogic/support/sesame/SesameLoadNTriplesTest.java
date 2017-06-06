package com.marklogic.support.sesame;

import com.marklogic.semantics.sesame.MarkLogicRepositoryConnection;
import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.Utils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicSesame;
import com.marklogic.support.providers.MarkLogicSesameRepositoryProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;

import java.io.IOException;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@MarkLogicSesame
@DisplayName("Benchmarking performance when loading N-Triples (.nt) files using the Sesame Repository API")
class SesameLoadNTriplesTest {

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 506Kb NT file (ron.nt)")
    public void testLoadingSmallNtFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(2), () -> {
            conn.add(Utils.getFileReader("nt/ron.nt"), "", RDFFormat.NTRIPLES);
        });
        assertEquals(3348, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 801Kb NT file (rmn.nt)")
    public void testLoadingAnotherSmallNtFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(2), () -> {
            conn.add(Utils.getFileReader("nt/rmn.nt"), "", RDFFormat.NTRIPLES);
        });
        assertEquals(5069, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load an 11.8MB NT file (dbpedia60k.nt)")
    public void testLoadingMediumNtFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(10), () -> {
            conn.add(Utils.getFileReader("nt/dbpedia60k.nt"), "", RDFFormat.NTRIPLES);
        });
        assertEquals(58512, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load an 18.2MB NT file (ron-data.nt)")
    public void testLoadingMedNtFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(10), () -> {
            conn.add(Utils.getFileReader("nt/ron-data.nt"), "", RDFFormat.NTRIPLES);
        });
        assertEquals(109672, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 18.2MB NT file (rmn-data.nt)")
    public void testLoadingAnotherMedNtFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(10), () -> {
            conn.add(Utils.getFileReader("nt/rmn-data.nt"), "", RDFFormat.NTRIPLES);
        });
        assertEquals(113904, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 96.4MB NT file (2013-02-14-panlex-dump.nt)")
    public void testLoadingLargeNtFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(50), () -> {
            conn.add(Utils.getFileReader("nt/2013-02-14-panlex-dump.nt"), "", RDFFormat.NTRIPLES);
        });
        assertEquals(746399, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

}

