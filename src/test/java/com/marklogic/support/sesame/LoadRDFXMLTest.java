package com.marklogic.support.sesame;

/**
 * Created by ableasdale on 26/05/2017.
 */

import com.marklogic.semantics.sesame.MarkLogicRepositoryConnection;
import com.marklogic.support.Utils;
import com.marklogic.support.annotations.Benchmark;
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
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@MarkLogicSesame
class LoadRDFXMLTest {

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 175KB RDF/XML file (countries.rdf)")
    public void testLoadingSmallRDFXMLFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofMillis(2000), () -> {
            conn.add(Utils.getFileReader("rdfxml/countries.rdf"), "", RDFFormat.RDFXML);
        });

        // TODO - also assert the total number of docs
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 21.8MB RDF/XML file (peel.rdf)")
    public void testLoadingMediumRDFXMLFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofMillis(60000), () -> {
            conn.add(Utils.getFileReader("rdfxml/peel.rdf"), "", RDFFormat.RDFXML);
        });

        // TODO - also assert the total number of docs
    }


    @Benchmark
    @Test
    @Disabled("file can't be parsed right now - need to figure out why...")
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 187.1MB RDF/XML file (geospecies.rdf)")
    public void testLoadingLargeRDFXMLFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofMillis(50000), () -> {
            conn.add(Utils.getFileReader("rdfxml/geospecies.rdf"), "", RDFFormat.RDFXML);
        });

        // TODO - also assert the total number of docs
    }
}