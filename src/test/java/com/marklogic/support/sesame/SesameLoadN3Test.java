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

/**
 * Created by ableasdale on 26/05/2017.
 */


@MarkLogicSesame
@DisplayName("Benchmarking performance when loading Notation3 (.n3) files using the Sesame Repository API")
public class SesameLoadN3Test {

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 39.5MB Notation 3 (N3) file (event-dump.n3)")
    public void testLoadingMediumN3File() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(50), () -> {
            conn.add(Utils.getFileReader("n3/event-dump.n3"), "", RDFFormat.N3);
        });
        assertEquals(682466, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 57.8MB Notation 3 (N3) file (sec.n3)")
    public void testLoadingLargeN3File() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(130), () -> {
            conn.add(Utils.getFileReader("n3/sec.n3"), "", RDFFormat.N3);
        });
        assertEquals(1813135, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

}
