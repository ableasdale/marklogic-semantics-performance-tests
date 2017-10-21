package com.marklogic.support.jena;

import com.marklogic.semantics.rdf4j.MarkLogicRepositoryConnection;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicJena;
import com.marklogic.support.annotations.MarkLogicRDF4J;
import com.marklogic.support.extensions.MarkLogicJenaExtension;
import com.marklogic.support.providers.MarkLogicRDF4JRepositoryProvider;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import com.marklogic.support.util.FileUtils;
import com.marklogic.support.util.SPARQLUtils;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("trig")
@MarkLogicJena
@DisplayName("Benchmarking performance when loading TriG (.trig) files Jena Client API")
public class JenaLoadTriGTest {

    @Disabled
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 502Kb TriG file (charging-stations-export-20170530-095530.trig)")
    void testLoadingSmallTriGFile() {
        assertTimeoutPreemptively(ofSeconds(250), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG.getDefaultGraph(), "src/main/resources/trig/charging-stations-export-20170530-095530.trig", Lang.TRIG));
        assertEquals(8900, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(8900, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Disabled
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 1.9MB TriG file (example.trig)")
    void testLoadingAnotherSmallTriGFile() {
        assertTimeoutPreemptively(ofSeconds(250), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG.getDefaultGraph(), "src/main/resources/trig/example.trig", Lang.TRIG));
        assertEquals(13671, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(13671, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Disabled
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 62.3MB TriG file (wp-monthy_all.trig)")
    void testLoadingLargeTriGFile() {
        assertTimeoutPreemptively(ofSeconds(250), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG.getDefaultGraph(), "src/main/resources/trig/wp-monthy_all.trig", Lang.TRIG));
        assertEquals(668367, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(668367, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}
