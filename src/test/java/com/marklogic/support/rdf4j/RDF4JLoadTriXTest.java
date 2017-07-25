package com.marklogic.support.rdf4j;

import com.marklogic.semantics.rdf4j.MarkLogicRepositoryConnection;
import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.Utils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicRDF4J;
import com.marklogic.support.providers.MarkLogicSesameRepositoryProvider;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParseException;

import java.io.IOException;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 30/05/2017.
 */
@Tag("ignore")
@MarkLogicRDF4J
public class RDF4JLoadTriXTest {


    /*
    TODO - Why does this fail with "Invalid mime type:  reason: Unable to determine content type of payload.  Received application/trix"
     */

    @Benchmark
    @Disabled("Fails right now - figure out why")
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 2.1MB TriX file (charging-stations-export-20170530-095413.xml)")
    public void testLoadingSmallTriXFile() throws RepositoryException, IOException, RDFParseException {
        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();
        assertTimeoutPreemptively(ofMillis(30000), () -> {
            conn.add(Utils.getFileReader("trix/charging-stations-export-20170530-095413.xml"), "", RDFFormat.TRIX);
        });
        assertEquals(3231, SPARQLUtils.countAllTriples(conn));
        conn.close();
    }

}
