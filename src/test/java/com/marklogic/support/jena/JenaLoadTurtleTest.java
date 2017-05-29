package com.marklogic.support.jena;


import com.marklogic.semantics.jena.MarkLogicDatasetGraph;
import com.marklogic.semantics.jena.MarkLogicDatasetGraphFactory;
import com.marklogic.support.annotations.MarkLogicJavaClient;
import com.marklogic.support.annotations.MarkLogicJena;
import com.marklogic.support.providers.MarkLogicJavaClientProvider;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFParseException;

import java.io.IOException;

import static com.marklogic.support.Utils.getFileHandleForTurtleFile;
import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 29/05/2017.
 */
@MarkLogicJena
public class JenaLoadTurtleTest {


    // TODO - this test currently fails... why?

    @Test
    @RepeatedTest(2)
    @DisplayName("Using the Jena Client API to load a 51MB Turtle file (history.ttl)")
    public void testLoadingLargeTurtleFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicDatasetGraph dsg = MarkLogicDatasetGraphFactory
                .createDatasetGraph(MarkLogicJavaClientProvider.getClient());

        RDFDataMgr.read(dsg, "src/main/resources/nt/ron.nt", Lang.NTRIPLES);

        /*
        UpdateRequest update = UpdateFactory.create(insertData);
        UpdateProcessor processor = UpdateExecutionFactory.create(update, dsg);
        processor.execute(); */
/*
        assertTimeoutPreemptively(ofMillis(35000), () -> {
            MarkLogicJavaClientProvider.getClient().newGraphManager().write("myExample/graphURI", getFileHandleForTurtleFile("turtle/history.ttl"));
        });
*/

        // TODO - also assert the total number of docs
    }
}
