package com.marklogic.support.javaclient;

import com.marklogic.client.io.StringHandle;
import com.marklogic.client.semantics.SPARQLMimeTypes;
import com.marklogic.client.semantics.SPARQLQueryDefinition;
import com.marklogic.client.semantics.SPARQLQueryManager;
import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicJavaClient;
import com.marklogic.support.providers.MarkLogicJavaClientProvider;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static com.marklogic.support.Utils.getFileHandleForTurtleFile;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 29/05/2017.
 */
@MarkLogicJavaClient
@DisplayName("Benchmarking performance when loading Turtle (.ttl) files using the MarkLogic Java Client API")
public class JavaClientTurtleTest {


    /*


      @DisplayName("Using the Jena Client API )
    public void testLoadingSmallXTurtleFile() {
        assertTimeoutPreemptively(ofSeconds(10),
     */
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 596Kb x-turtle file (charging-stations-export-20170530-095533.ttl)")
    public void testLoadingSmallXTurtleFile() {

        assertTimeoutPreemptively(ofSeconds(70), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write("myExample/graphURI", getFileHandleForTurtleFile("turtle/charging-stations-export-20170530-095533.ttl")));
        assertEquals(8900, SPARQLUtils.countAllTriples(MarkLogicJavaClientProvider.getClient()));
    }


    @Disabled
    @Benchmark
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 51MB Turtle file (history.ttl)")
    public void testLoadingLargeTurtleFile() {

        assertTimeoutPreemptively(ofSeconds(70), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write("myExample/graphURI", getFileHandleForTurtleFile("turtle/history.ttl")));


        SPARQLQueryManager sparqlMgr = MarkLogicJavaClientProvider.getClient().newSPARQLQueryManager();
        String sparql = "select (count(*) as ?total) where { ?s ?p ?o . }";
        SPARQLQueryDefinition query = sparqlMgr.newQueryDefinition(sparql);

        StringHandle handle = new StringHandle().withMimetype(SPARQLMimeTypes.SPARQL_CSV);

        String results = sparqlMgr.executeSelect(query, handle).get();
        System.out.println(results);
        // TODO - also assert the total number of docs
    }


}


