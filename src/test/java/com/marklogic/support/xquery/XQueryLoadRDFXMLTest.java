package com.marklogic.support.xquery;

import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogic;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import okhttp3.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static com.marklogic.support.providers.MarkLogicReSTApiClientProvider.createXQuerySemLoad;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("rdfxml")
@MarkLogic
@DisplayName("Benchmarking performance when loading Resource Description Framework (.rdf) files using XQuery")
class XQueryLoadRDFXMLTest {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 175KB RDF/XML file (capitals.rdf)")
    void testLoadingSmallRDFXMLFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("rdfxml/capitals.rdf", "rdfxml")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(2584, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 189KB RDF/XML file (currencies.rdf)")
    void testLoadingAnotherSmallRDFXMLFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("rdfxml/currencies.rdf", "rdfxml")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(3231, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 611KB RDF/XML file (countries.rdf)")
    void testLoadingYetAnotherSmallRDFXMLFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(5), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("rdfxml/countries.rdf", "rdfxml")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(9330, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using XQuery sem:load to load a 21.8MB RDF/XML file (peel.rdf)")
    void testLoadingMediumRDFXMLFile() {
        Response res = assertTimeoutPreemptively(ofSeconds(45), () -> MarkLogicReSTApiClientProvider.evalXQuery(createXQuerySemLoad("rdfxml/peel.rdf", "rdfxml")));
        assertEquals("OK", res.message());
        assertEquals(200, res.code());
        assertEquals(271369, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

}