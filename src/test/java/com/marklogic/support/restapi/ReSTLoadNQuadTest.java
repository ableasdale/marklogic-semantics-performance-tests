package com.marklogic.support.restapi;

import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicReST;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("nquad2")
@MarkLogicReST
@DisplayName("Benchmarking performance when loading NQuad (.nq) files using the ReST API endpoints")
public class ReSTLoadNQuadTest {

    @Test
    @Benchmark
    @DisplayName("Using the ReST API to load a 3.3MB NQuad file (2_12770.nq)")
    void testLoadingMediumSizeNQuadFile() {
        ClientResponse res = assertTimeoutPreemptively(ofSeconds(2), () -> MarkLogicReSTApiClientProvider.createPostForNQuads("nquads/2_12770.nq"));
        assertEquals(204, res.getStatus());
        // TODO - count triples after
    }
}
