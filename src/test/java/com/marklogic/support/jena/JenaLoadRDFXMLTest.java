package com.marklogic.support.jena;

import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicJena;
import com.marklogic.support.extensions.MarkLogicJenaExtension;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import com.marklogic.support.util.SPARQLUtils;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("rdfxml")
@MarkLogicJena
@DisplayName("Benchmarking performance when loading Resource Description Framework (.rdf) files using the Jena Client API")
public class JenaLoadRDFXMLTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 175KB RDF/XML file (countries.rdf)")
    void testLoadingSmallRDFXMLFile() {
        assertTimeoutPreemptively(ofSeconds(10), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG.getDefaultGraph(), "src/main/resources/rdfxml/countries.rdf", Lang.RDFXML));
        assertEquals(9330, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(9330, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 189KB RDF/XML file (currencies.rdf)")
    void testLoadingAnotherSmallRDFXMLFile() {
        assertTimeoutPreemptively(ofSeconds(10), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG.getDefaultGraph(), "src/main/resources/rdfxml/currencies.rdf", Lang.RDFXML));
        assertEquals(3231, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(3231, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Disabled
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 21.8MB RDF/XML file (peel.rdf)")
    void testLoadingMediumRDFXMLFile() {
        assertTimeoutPreemptively(ofSeconds(120), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG.getDefaultGraph(), "src/main/resources/rdfxml/peel.rdf", Lang.RDFXML));
        assertEquals(271369, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(271369, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }
}
