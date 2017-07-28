package com.marklogic.support.jena;

import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicJena;
import com.marklogic.support.extensions.MarkLogicJenaExtension;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 29/05/2017.
 */

@Tag("ignore")
@MarkLogicJena
@DisplayName("Benchmarking performance when loading Turtle (.ttl) files using the Jena Client API")
class JenaLoadTurtleTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 596Kb x-turtle file (charging-stations-export-20170530-095533.ttl)")
    void testLoadingSmallXTurtleFile() {
        assertTimeoutPreemptively(ofSeconds(10), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/turtle/charging-stations-export-20170530-095533.ttl", Lang.TURTLE));
        assertEquals(8900, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 779K Turtle file (units.ttl)")
    void testLoadingSmallTurtleFile() {
        assertTimeoutPreemptively(ofSeconds(18), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/turtle/units.ttl", Lang.TURTLE));
        assertEquals(23485, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 3.3MB Turtle file (unescothes.ttl)")
    void testLoadingMediumSizeTurtleFile() {
        assertTimeoutPreemptively(ofSeconds(60), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/turtle/unescothes.ttl", Lang.TURTLE));
        assertEquals(75202, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 51MB Turtle file (history.ttl)")
    void testLoadingLargeTurtleFile() {
        assertTimeoutPreemptively(ofSeconds(330), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/turtle/history.ttl", Lang.TURTLE));
        assertEquals(391551, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 130MB Turtle file (fulldump.ttl)")
    void testLoadingAnotherLargeTurtleFile() {
        assertTimeoutPreemptively(ofSeconds(1000), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/turtle/fulldump.ttl", Lang.TURTLE));
        assertEquals(204122, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}
