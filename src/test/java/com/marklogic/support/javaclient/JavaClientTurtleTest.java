package com.marklogic.support.javaclient;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.io.FileHandle;
import com.marklogic.client.semantics.GraphManager;
import com.marklogic.client.semantics.RDFMimeTypes;
import com.marklogic.semantics.sesame.MarkLogicRepositoryConnection;
import com.marklogic.support.Utils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicJavaClient;
import com.marklogic.support.providers.MarkLogicJavaClientProvider;
import com.marklogic.support.providers.MarkLogicSesameRepositoryProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

import static com.marklogic.support.Utils.getFileHandleForTurtleFile;
import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 29/05/2017.
 */
@MarkLogicJavaClient
public class JavaClientTurtleTest {

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 51MB Turtle file (history.ttl)")
    public void testLoadingLargeTurtleFile() throws RepositoryException, IOException, RDFParseException {



        assertTimeoutPreemptively(ofMillis(35000), () -> {
            MarkLogicJavaClientProvider.getClient().newGraphManager().write("myExample/graphURI", getFileHandleForTurtleFile("turtle/history.ttl"));
        });


        // TODO - also assert the total number of docs
    }
}
