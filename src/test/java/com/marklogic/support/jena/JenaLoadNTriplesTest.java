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

@Tag("ignore")
@MarkLogicJena
@DisplayName("Benchmarking performance when loading N-Triples (.nt) files using the Jena Client API")
class JenaLoadNTriplesTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 506Kb N-Triples file (ron.nt)")
    void testLoadingSmallNtFile() {
        assertTimeoutPreemptively(ofSeconds(5), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/nt/ron.nt", Lang.NT));
        assertEquals(3348, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 801Kb N-Triples file (rmn.nt)")
    void testLoadingAnotherSmallNtFile() {
        assertTimeoutPreemptively(ofSeconds(5), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/nt/rmn.nt", Lang.NT));
        assertEquals(5069, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load an 11.8MB N-Triples file (dbpedia60k.nt)")
    void testLoadingMediumNtFile() {
        assertTimeoutPreemptively(ofSeconds(10), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/nt/dbpedia60k.nt", Lang.NT));
        assertEquals(58512, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load an 18.2MB N-Triples file (ron-data.nt)")
    void testLoadingMedNtFile() {
        assertTimeoutPreemptively(ofSeconds(18), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/nt/ron-data.nt", Lang.NT));
        assertEquals(109672, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 18.2MB N-Triples file (rmn-data.nt)")
    void testLoadingAnotherMedNtFile() {
        assertTimeoutPreemptively(ofSeconds(18), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/nt/rmn-data.nt", Lang.NT));
        assertEquals(113904, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic RDF4J API to load a 96.4MB N-Triples file (2013-02-14-panlex-dump.nt)")
    void testLoadingLargeNtFile() {
        assertTimeoutPreemptively(ofSeconds(50), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/nt/rmn-data.nt", Lang.NT));
        assertEquals(746399, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}
