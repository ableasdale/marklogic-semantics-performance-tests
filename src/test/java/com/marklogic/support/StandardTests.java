package com.marklogic.support;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Created by ableasdale on 26/05/2017.
 */
public class StandardTests {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @BeforeAll
    static void initAll() {
        LOG.info("before all");
    }

    @BeforeEach
    void init() {
        LOG.info("before each");
    }

    @AfterEach
    void tearDown() {
        LOG.info("tear down each");
    }

    @AfterAll
    static void tearDownAll() {
        LOG.info("tear down all");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
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