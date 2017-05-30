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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 28/05/2017.
 */

@MarkLogicSesame
public class SesameLoadTurtleTest {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 596Kb x-turtle file (charging-stations-export-20170530-095533.ttl)")
    public void testLoadingSmallXTurtleFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofMillis(2000), () -> {
            conn.add(Utils.getFileReader("turtle/charging-stations-export-20170530-095533.ttl"), "", RDFFormat.TURTLE);
        });

        // TODO - also assert the total number of docs
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 779K Turtle file (units.ttl)")
    public void testLoadingSmallTurtleFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofMillis(2000), () -> {
            conn.add(Utils.getFileReader("turtle/units.ttl"), "", RDFFormat.TURTLE);
        });

        // TODO - also assert the total number of docs
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 3.3MB Turtle file (unescothes.ttl)")
    public void testLoadingMediumSizeTurtleFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofMillis(50000), () -> {
            conn.add(Utils.getFileReader("turtle/unescothes.ttl"), "", RDFFormat.TURTLE);
        });

        // TODO - also assert the total number of docs
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 51MB Turtle file (history.ttl)")
    public void testLoadingLargeTurtleFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofMillis(50000), () -> {
            conn.add(Utils.getFileReader("turtle/history.ttl"), "", RDFFormat.TURTLE);
        });

        // TODO - also assert the total number of docs
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 130MB Turtle file (fulldump.ttl)")
    public void testLoadingAnotherLargeTurtleFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofMillis(90000), () -> {
            conn.add(Utils.getFileReader("turtle/fulldump.ttl"), "", RDFFormat.TURTLE);
        });

        // TODO - also assert the total number of docs
    }
}
