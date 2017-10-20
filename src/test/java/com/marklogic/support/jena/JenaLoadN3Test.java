package com.marklogic.support.jena;

import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicJena;
import com.marklogic.support.extensions.MarkLogicJenaExtension;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import com.marklogic.support.util.SPARQLUtils;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.rio.RDFParseException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import java.io.IOException;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("jena")
@MarkLogicJena
@DisplayName("Benchmarking performance when loading Notation3 (.n3) files using the Jena Client API")
public class JenaLoadN3Test {

    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 39.5MB Notation 3 (N3) file (event-dump.n3)")
    void testLoadingMediumN3File() throws RepositoryException, IOException, RDFParseException {
        assertTimeoutPreemptively(ofSeconds(250), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG.getDefaultGraph(), "src/main/resources/n3/event-dump.n3", Lang.N3));
        assertEquals(682466, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(682466, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

    @Disabled
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 57.8MB Notation 3 (N3) file (sec.n3)")
    void testLoadingLargeN3File() throws RepositoryException, IOException, RDFParseException {
        assertTimeoutPreemptively(ofSeconds(250), () -> RDFDataMgr.read(MarkLogicJenaExtension.DSG.getDefaultGraph(), "src/main/resources/n3/sec.n3", Lang.N3));
        assertEquals(1813135, SPARQLUtils.countAllTriples(MarkLogicJenaExtension.DSG));
        assertEquals(1813135, MarkLogicReSTApiClientProvider.getTripleCount());
        assertEquals(2, MarkLogicReSTApiClientProvider.getGraphCount());
    }

}
