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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 26/05/2017.
 */

@MarkLogicSesame
@DisplayName("Benchmarking performance when loading N-Quad (.nq) files using the Sesame Repository API")
public class SesameLoadNQuadTest {

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 41.8MB N-Quads file (sider-indications_raw.nq)")
    public void testLoadingMediumNQuadsFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofSeconds(20), () -> {
            conn.add(Utils.getFileReader("nquads/sider-indications_raw.nq"), "", RDFFormat.NQUADS);
        });

        assertEquals(163774, SPARQLUtils.countAllTriples(conn));
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 262.1MB N-Quads file (sider-label_mapping.nq)")
    public void testLoadingLargeNQuadsFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofSeconds(80), () -> {
            conn.add(Utils.getFileReader("nquads/sider-label_mapping.nq"), "", RDFFormat.NQUADS);
        });

        assertEquals(665161, SPARQLUtils.countAllTriples(conn));
    }
}