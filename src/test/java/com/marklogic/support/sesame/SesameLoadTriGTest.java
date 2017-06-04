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

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 30/05/2017.
 */
@MarkLogicSesame
@DisplayName("Benchmarking performance when loading TriG (.trig) files using the Sesame Repository API")
public class SesameLoadTriGTest {

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 502Kb TriG file (charging-stations-export-20170530-095530.trig)")
    public void testLoadingSmallTriGFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofMillis(3000), () -> {
            conn.add(Utils.getFileReader("trig/charging-stations-export-20170530-095530.trig"), "", RDFFormat.TRIG);
        });

        assertEquals(8900, SPARQLUtils.countAllTriples(conn));
    }
}
