package com.marklogic.support.restapi;

import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicReST;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("rdfxml")
@MarkLogicReST
@DisplayName("Benchmarking performance when loading Resource Description Framework (.rdf) files using the ReST API endpoint")
class ReSTLoadRDFXMLTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 175KB RDF/XML file (countries.rdf)")
    void testLoadingSmallRDFXMLFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.createPostForRDFXML("rdfxml/countries.rdf"));
        assertEquals(201, res.getStatus());
        assertEquals(9330, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 189KB RDF/XML file (currencies.rdf)")
    void testLoadingAnotherSmallRDFXMLFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.createPostForRDFXML("rdfxml/currencies.rdf"));
        assertEquals(201, res.getStatus());
        assertEquals(3231, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the ReST API to load a 21.8MB RDF/XML file (peel.rdf)")
    void testLoadingMediumRDFXMLFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(45), () -> MarkLogicReSTApiClientProvider.createPostForRDFXML("rdfxml/peel.rdf"));
        assertEquals(201, res.getStatus());
        assertEquals(271369, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

}