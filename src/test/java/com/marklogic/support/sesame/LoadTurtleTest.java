package com.marklogic.support.sesame;

import com.marklogic.semantics.sesame.MarkLogicRepositoryConnection;
import com.marklogic.support.MarkLogicSesameRepositoryProvider;
import com.marklogic.support.Utils;
import com.marklogic.support.annotations.Benchmark;

import com.marklogic.support.annotations.MarkLogicSesame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 28/05/2017.
 */


@MarkLogicSesame
public class LoadTurtleTest {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @Benchmark
    @Test
    @RepeatedTest(3)
    @DisplayName("Using the MarkLogic Sesame API to load a 779K Turtle file (units.ttl)")
    public void testLoadingSmallTurtleFile() throws RepositoryException, IOException, RDFParseException {



        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofMillis(1500), () -> {
            conn.add(Utils.getFileReader("turtle/units.ttl"), "", RDFFormat.TURTLE);
        });

        // TODO - also assert the total number of docs
    }
}
