package com.marklogic.support.rdf4j;

import com.marklogic.semantics.rdf4j.MarkLogicRepositoryConnection;
import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.Utils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicRDF4J;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import com.marklogic.support.providers.MarkLogicSesameRepositoryProvider;
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

@Tag("nquads3")
@MarkLogicRDF4J
@DisplayName("Benchmarking performance when loading N-Quad (.nq) files using the Sesame Repository API")
class RDF4JLoadNQuadTest {

    //private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 41.8MB N-Quads file (sider-indications_raw.nq)")
    void testLoadingMediumNQuadsFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(20), () -> {
            conn.add(Utils.getFileReader("nquads/sider-indications_raw.nq"), "", RDFFormat.NQUADS);
        });
        assertEquals(163774, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 262.1MB N-Quads file (sider-label_mapping.nq)")
    void testLoadingLargeNQuadsFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(80), () -> {
            conn.add(Utils.getFileReader("nquads/sider-label_mapping.nq"), "", RDFFormat.NQUADS);
        });
        assertEquals(665161, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 28.8MB N-Quads file (toDelete.nq)")
    void testLoadingtoDeleteFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(80), () -> {
            conn.add(Utils.getFileReader("nquads/toDelete.nq"), "", RDFFormat.NQUADS);
        });
        assertEquals(116291, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(12003, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 20.5MB N-Quads file (1_86286.nq)")
    void testLoadingSampleOne() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(80), () -> {
            conn.add(Utils.getFileReader("nquads/1_86286.nq"), "", RDFFormat.NQUADS);
        });
        assertEquals(86286, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(12003, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 3.3MB N-Quads file (2_12770.nq)")
    void testLoadingSampleTwo() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(60), () -> {
            conn.add(Utils.getFileReader("nquads/2_12770.nq"), "", RDFFormat.NQUADS);
        });
        conn.close();
        assertEquals(12770, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2905, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 13.9MB N-Quads file (3_54187.nq)")
    void testLoadingSampleThree() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(80), () -> {
            conn.add(Utils.getFileReader("nquads/3_54187.nq"), "", RDFFormat.NQUADS);
        });
        assertEquals(54187, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(12157, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 35.3MB N-Quads file (4_138495.nq)")
    void testLoadingSampleFour() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(80), () -> {
            conn.add(Utils.getFileReader("nquads/4_138495.nq"), "", RDFFormat.NQUADS);
        });
        assertEquals(138495, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(25995, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 16MB N-Quads file (5_63578.nq)")
    void testLoadingSampleFive() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(80), () -> {
            conn.add(Utils.getFileReader("nquads/5_63578.nq"), "", RDFFormat.NQUADS);
        });
        assertEquals(63578, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(12323, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 31.6MB NQuad file (6_125268.nq)")
    void testLoadingSampleSix() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(80), () -> {
            conn.add(Utils.getFileReader("nquads/6_125268.nq"), "", RDFFormat.NQUADS);
        });
        assertEquals(125268, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(24263, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}