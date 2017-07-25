package com.marklogic.support.sesame;

import com.marklogic.semantics.rdf4j.MarkLogicRepositoryConnection;
import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.Utils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicSesame;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import com.marklogic.support.providers.MarkLogicSesameRepositoryProvider;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParseException;
import org.junit.jupiter.api.Disabled;
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

/**
 * Created by ableasdale on 26/05/2017.
 */

@Tag("nquads3")
@MarkLogicSesame
@DisplayName("Benchmarking performance when loading N-Quad (.nq) files using the Sesame Repository API")
public class SesameLoadNQuadTest {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Disabled
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 41.8MB N-Quads file (sider-indications_raw.nq)")
    public void testLoadingMediumNQuadsFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(20), () -> {
            conn.add(Utils.getFileReader("nquads/sider-indications_raw.nq"), "", RDFFormat.NQUADS);
        });
        assertEquals(163774, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Disabled
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 262.1MB N-Quads file (sider-label_mapping.nq)")
    public void testLoadingLargeNQuadsFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(80), () -> {
            conn.add(Utils.getFileReader("nquads/sider-label_mapping.nq"), "", RDFFormat.NQUADS);
        });
        assertEquals(665161, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    // Specific tests below:

    @Disabled
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load toDelete.nq file (toDelete.nq)")
    public void testLoadingtoDeleteFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(80), () -> {
            conn.add(Utils.getFileReader("nquads/toDelete.nq"), "", RDFFormat.NQUADS);
        });
        assertEquals(665161, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Disabled
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load Sample NQuad file 1 (1_86286.nq)")
    public void testLoadingSampleOne() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(80), () -> {
            conn.add(Utils.getFileReader("nquads/1_86286.nq"), "", RDFFormat.NQUADS);
        });
        assertEquals(86286, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load Sample NQuad file 2 (2_12770.nq)")
    public void testLoadingSampleTwo() throws RepositoryException, IOException, RDFParseException {
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
    @DisplayName("Using the MarkLogic Sesame API to load Sample NQuad file 3 (3_54187.nq)")
    public void testLoadingSampleThree() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(80), () -> {
            conn.add(Utils.getFileReader("nquads/3_54187.nq"), "", RDFFormat.NQUADS);
        });
        assertEquals(54187, SPARQLUtils.countAllTriples(conn));
        conn.close();
        assertEquals(12157, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Disabled
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load Sample NQuad file 4 (4_138495.nq)")
    public void testLoadingSampleFour() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(80), () -> {
            conn.add(Utils.getFileReader("nquads/4_138495.nq"), "", RDFFormat.NQUADS);
        });
        assertEquals(138495, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Disabled
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load Sample NQuad file 5 (5_63578.nq)")
    public void testLoadingSampleFive() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(80), () -> {
            conn.add(Utils.getFileReader("nquads/5_63578.nq"), "", RDFFormat.NQUADS);
        });
        assertEquals(63578, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Disabled
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load Sample NQuad file 6 (6_125268.nq)")
    public void testLoadingSampleSix() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(80), () -> {
            conn.add(Utils.getFileReader("nquads/6_125268.nq"), "", RDFFormat.NQUADS);
        });
        assertEquals(125268, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

}