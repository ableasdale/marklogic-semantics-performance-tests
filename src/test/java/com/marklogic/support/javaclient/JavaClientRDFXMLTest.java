package com.marklogic.support.javaclient;

import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicJavaClient;
import com.marklogic.support.providers.MarkLogicJavaClientProvider;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static com.marklogic.client.semantics.GraphManager.DEFAULT_GRAPH;
import static com.marklogic.support.Utils.getFileHandleForRdfXmlFile;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("rdfxml")
@MarkLogicJavaClient
@DisplayName("Benchmarking performance when loading Resource Description Framework (.rdf) files using the MarkLogic Java Client API")
public class JavaClientRDFXMLTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 175KB RDF/XML file (countries.rdf)")
    void testLoadingSmallRDFXMLFile() {
        assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(DEFAULT_GRAPH, getFileHandleForRdfXmlFile("rdfxml/countries.rdf")));
        assertEquals(9330, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 189KB RDF/XML file (currencies.rdf)")
    void testLoadingAnotherSmallRDFXMLFile() {
        assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(DEFAULT_GRAPH, getFileHandleForRdfXmlFile("rdfxml/currencies.rdf")));
        assertEquals(3231, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 21.8MB RDF/XML file (peel.rdf)")
    void testLoadingMediumRDFXMLFile() {
        assertTimeoutPreemptively(ofSeconds(40), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write(DEFAULT_GRAPH, getFileHandleForRdfXmlFile("rdfxml/peel.rdf")));
        assertEquals(271369, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

}
