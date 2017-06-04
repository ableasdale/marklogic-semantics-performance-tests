package com.marklogic.support.javaclient;

import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicJavaClient;
import com.marklogic.support.providers.MarkLogicJavaClientProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static com.marklogic.support.Utils.getFileHandleForTurtleFile;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 29/05/2017.
 */
@MarkLogicJavaClient
@DisplayName("Benchmarking performance when loading Turtle (.ttl) files using the MarkLogic Java Client API")
public class JavaClientTurtleTest {

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the Java Client API to load a 51MB Turtle file (history.ttl)")
    public void testLoadingLargeTurtleFile() {

        assertTimeoutPreemptively(ofSeconds(70), () -> MarkLogicJavaClientProvider.getClient().newGraphManager().write("myExample/graphURI", getFileHandleForTurtleFile("turtle/history.ttl")));


        // TODO - also assert the total number of docs
    }
}
