package com.marklogic.support.sesame;

import com.marklogic.support.annotations.Benchmark;

import com.marklogic.support.annotations.MarkLogicSesame;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ableasdale on 28/05/2017.
 */

@Benchmark
@MarkLogicSesame
public class LoadTurtleTest {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    public void testLoadingSmallTurtleFile() {
        LOG.info("doing some testing");
        assertEquals(5, 1 + 4);
    }
}
