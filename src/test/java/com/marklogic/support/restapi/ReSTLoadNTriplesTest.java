package com.marklogic.support.restapi;

import com.marklogic.semantics.rdf4j.MarkLogicRepositoryConnection;
import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.Utils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicReST;
import com.marklogic.support.providers.MarkLogicRDF4JRepositoryProvider;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import com.sun.jersey.api.client.ClientResponse;
import org.apache.jena.base.Sys;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParseException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("ntriples2")
@MarkLogicReST
@DisplayName("Benchmarking performance when loading N-Triples (.nt) files using the ReST API endpoint")
public class ReSTLoadNTriplesTest {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 506Kb NT file (ron.nt)")
    public void testLoadingSmallNtFile() throws RepositoryException, IOException, RDFParseException {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(20), () -> MarkLogicReSTApiClientProvider.createPostForNTriples("nt/ron.nt"));
        assertEquals("Created", res.getClientResponseStatus().getReasonPhrase());
        assertEquals(201, res.getStatus()); // returns 201 because it returns content in the response body
        assertEquals(3348, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    /*
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 801Kb NT file (rmn.nt)")
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
    @DisplayName("Using the ReST API to load an 11.8MB NT file (dbpedia60k.nt)")
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
    @DisplayName("Using the ReST API to load an 18.2MB NT file (ron-data.nt)")
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
    @DisplayName("Using the ReST API to load a 18.2MB NT file (rmn-data.nt)")
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
    @DisplayName("Using the ReST API to load a 96.4MB NT file (2013-02-14-panlex-dump.nt)")
    public void testLoadingLargeNtFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(50), () -> {
            conn.add(Utils.getFileReader("nt/2013-02-14-panlex-dump.nt"), "", RDFFormat.NTRIPLES);
        });
        assertEquals(746399, SPARQLUtils.countAllTriples(conn));
        conn.close();
    } */

}
