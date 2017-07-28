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
@DisplayName("Benchmarking performance when loading N-Quads (.nq) files using the Jena Client API")
class JenaLoadNQuadsTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 20.5MB N-Quads file (1_86286.nq)")
    void testLoadingSampleOne() {
        assertTimeoutPreemptively(ofSeconds(80), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/nquads/1_86286.nq", Lang.NQUADS));
        assertEquals(86286, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));

        //assertEquals(86286, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(12003, MarkLogicReSTApiClientProvider.getGraphCount());
    }


    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 3.3MB N-Quads file (2_12770.nq)")
    void testLoadingSampleTwo() {
        assertTimeoutPreemptively(ofSeconds(80), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/nquads/2_12770.nq", Lang.NQUADS));
        assertEquals(12770, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));

        //assertEquals(12770, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2905, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 13.9MB N-Quads file (3_54187.nq)")
    void testLoadingSampleThree() {
        assertTimeoutPreemptively(ofSeconds(80), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/nquads/3_54187.nq", Lang.NQUADS));
        assertEquals(54187, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));

        //assertEquals(12770, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(12157, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 35.3MB N-Quads file (4_138495.nq)")
    void testLoadingSampleFour() {
        assertTimeoutPreemptively(ofSeconds(150), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/nquads/4_138495.nq", Lang.NQUADS));
        assertEquals(138495, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));

        //assertEquals(12770, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(25995, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 16MB N-Quads file (5_63578.nq)")
    void testLoadingSampleFive() {
        assertTimeoutPreemptively(ofSeconds(80), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/nquads/5_63578.nq", Lang.NQUADS));
        assertEquals(63578, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));

        //assertEquals(12770, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(12323, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 31.6MB N-Quads file (6_125268.nq)")
    void testLoadingSampleSix() {
        assertTimeoutPreemptively(ofSeconds(150), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG, "src/main/resources/nquads/6_125268.nq", Lang.NQUADS));
        assertEquals(125268, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));

        //assertEquals(12770, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(24263, MarkLogicReSTApiClientProvider.getGraphCount());
    }

}
