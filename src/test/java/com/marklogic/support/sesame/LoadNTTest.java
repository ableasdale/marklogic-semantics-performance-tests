package com.marklogic.support.sesame;

import com.marklogic.semantics.sesame.MarkLogicRepositoryConnection;
import com.marklogic.support.Utils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogic;
import com.marklogic.support.annotations.MarkLogicSesame;
import com.marklogic.support.providers.MarkLogicSesameRepositoryProvider;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;

import java.io.IOException;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@DisplayName("A set of tests designed for .nt files")
@MarkLogicSesame
class LoadNTTest {

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 506Kb NT file (ron.nt)")
    public void testLoadingSmallNtFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofMillis(30000), () -> {
            conn.add(Utils.getFileReader("nt/ron.nt"), "", RDFFormat.NTRIPLES);
        });

        // TODO - also assert the total number of docs
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 801Kb NT file (rmn.nt)")
    public void testLoadingAnotherSmallNtFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofMillis(30000), () -> {
            conn.add(Utils.getFileReader("nt/rmn.nt"), "", RDFFormat.NTRIPLES);
        });

        // TODO - also assert the total number of docs
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 19GB NT file (ron-data.nt)")
    public void testLoadingMedNtFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofMillis(9000), () -> {
            conn.add(Utils.getFileReader("nt/ron-data.nt"), "", RDFFormat.NTRIPLES);
        });

        // TODO - also assert the total number of docs
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 18.2GB NT file (rmn-data.nt)")
    public void testLoadingAnotherMedNtFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofMillis(9000), () -> {
            conn.add(Utils.getFileReader("nt/rmn-data.nt"), "", RDFFormat.NTRIPLES);
        });

        // TODO - also assert the total number of docs
    }

    @Benchmark
    @Test
    @Disabled("Fails")
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 12.8MB NT file (Lexicon_Stratotype.nt)")
    public void testLoadingLargeNQuadsFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofMillis(30000), () -> {
            conn.add(Utils.getFileReader("nt/Lexicon_Stratotype.nt"), "", RDFFormat.NTRIPLES);
        });

        // TODO - also assert the total number of docs
    }

    @Test
    @Disabled("Example from JUnit 5 Docs...")
    @DisplayName("╯°□°）╯")
    void testWithDisplayNameContainingSpecialCharacters() {
        assertEquals(2, (1 + 1), "1 + 1 should equal 2");
    }

    @Test
    @Disabled("Example from JUnit 5 Docs...")
    @DisplayName("😱")
    void testWithDisplayNameContainingEmoji() {
        assertEquals(3, (1 + 2), "1 + 1 should equal 2");
    }

}

