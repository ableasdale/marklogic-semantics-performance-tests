package com.marklogic.support;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.junit.Assert.assertEquals;

/**
 * Created by ableasdale on 26/05/2017.
 */
@Benchmark
@MarkLogic
public class StandardTests {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @BeforeAll
    static void initAll() {
        LOG.info("before all");
    }

    @AfterAll
    static void tearDownAll() {
        LOG.info("tear down all");
    }

    @BeforeEach
    void init() {
        LOG.info("before each");
    }

    @AfterEach
    void tearDown() {
        LOG.info("tear down each");
    }

    @DisplayName("The skipped test")
    @Test
        // @Disabled("for demonstration purposes")
    void skippedTest() {
        LOG.info("this is supposed to be a test");
        assertEquals(true, true);
        // not executed
    }

    interface UsesDatabase {
        @BeforeAll
        static void initializeDatabaseConnections() {
            LOG.info("Setting up database");
            // initialize database connections
        }
    }

}

/* @Target({ ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(ExternalDatabaseExtension.class)
public @interface Database { } */