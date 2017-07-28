package com.marklogic.support.rdf4j;

import com.marklogic.semantics.rdf4j.MarkLogicRepositoryConnection;
import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.Utils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicRDF4J;
import com.marklogic.support.providers.MarkLogicRDF4JRepositoryProvider;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 30/05/2017.
 */

@Tag("trig")
@MarkLogicRDF4J
@DisplayName("Benchmarking performance when loading TriG (.trig) files using the Sesame Repository API")
class RDF4JLoadTriGTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 502Kb TriG file (charging-stations-export-20170530-095530.trig)")
    void testLoadingSmallTriGFile() {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(5), () -> {
            conn.add(Utils.getFileReader("trig/charging-stations-export-20170530-095530.trig"), "", RDFFormat.TRIG);
        });
        assertEquals(8900, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 1.9MB TriG file (example.trig)")
    void testLoadingAnotherSmallTriGFile() {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(10), () -> {
            conn.add(Utils.getFileReader("trig/example.trig"), "", RDFFormat.TRIG);
        });
        assertEquals(13671, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 62.3MB TriG file (wp-monthy_all.trig)")
    void testLoadingLargeTriGFile() {
        MarkLogicRepositoryConnection conn = MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofSeconds(150), () -> {
            conn.add(Utils.getFileReader("trig/wp-monthy_all.trig"), "", RDFFormat.TRIG);
        });
        assertEquals(668367, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

}
