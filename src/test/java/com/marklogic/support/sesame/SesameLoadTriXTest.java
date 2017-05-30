package com.marklogic.support.sesame;

import com.marklogic.semantics.sesame.MarkLogicRepositoryConnection;
import com.marklogic.support.Utils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicSesame;
import com.marklogic.support.providers.MarkLogicSesameRepositoryProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;

import java.io.IOException;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 30/05/2017.
 */
@MarkLogicSesame
public class SesameLoadTriXTest {


    /*
    TODO - Why does this fail with "Invalid mime type:  reason: Unable to determine content type of payload.  Received application/trix"
     */

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 2.1MB TriX file (charging-stations-export-20170530-095413.xml)")
    public void testLoadingSmallTriXFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofMillis(30000), () -> {
            conn.add(Utils.getFileReader("trix/charging-stations-export-20170530-095413.xml"), "", RDFFormat.TRIX);
        });

        // TODO - also assert the total number of docs
    }

}
