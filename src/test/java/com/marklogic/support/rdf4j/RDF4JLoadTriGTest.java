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

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 30/05/2017.
 */

@Tag("ignore")
@MarkLogicRDF4J
@DisplayName("Benchmarking performance when loading TriG (.trig) files using the Sesame Repository API")
public class RDF4JLoadTriGTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 502Kb TriG file (charging-stations-export-20170530-095530.trig)")
    public void testLoadingSmallTriGFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofMillis(3000), () -> {
            conn.add(Utils.getFileReader("trig/charging-stations-export-20170530-095530.trig"), "", RDFFormat.TRIG);
        });
        assertEquals(8900, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }
}
