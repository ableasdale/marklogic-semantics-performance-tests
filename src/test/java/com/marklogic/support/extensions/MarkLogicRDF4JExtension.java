package com.marklogic.support.extensions;

import com.marklogic.support.providers.MarkLogicRDF4JRepositoryProvider;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by ableasdale on 28/05/2017.
 */
public class MarkLogicRDF4JExtension implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback, Extension {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE ALL TESTS)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info("■ MarkLogic RDF4J API Client (BEFORE ALL TESTS) ■");
        assertEquals(200, MarkLogicReSTApiClientProvider.createPostForClearingDatabase().code());
        assertTrue(MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection().isOpen());
//        assertTrue(MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection().isEmpty());
//        assertTrue(MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection().size() == 0);
        assertEquals(0, MarkLogicReSTApiClientProvider.getTripleCount());
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (AFTER ALL TESTS)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info("■ MarkLogic RDF4J API Client (AFTER ALL TESTS) ■");
//        assertTrue(MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection().isEmpty());
        //       assertTrue(MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection().size() == 0);
        assertEquals(200, MarkLogicReSTApiClientProvider.createPostForClearingDatabase().code());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (AFTER TEST) :: Clearing all triples", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info(String.format(" ■■ RDF4J API Client (AFTER) ■■ %s", context.getDisplayName()));
        //SPARQLUtils.deleteAllTriples(MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection());
        assertEquals(200, MarkLogicReSTApiClientProvider.createPostForClearingDatabase().code());
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE TEST)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info(String.format(" ■■ RDF4J API Client (BEFORE) ■■ %s", context.getDisplayName()));
        //assertTrue(SPARQLUtils.isDatabaseEmpty(MarkLogicRDF4JRepositoryProvider.getMarkLogicRepositoryConnection()));
        assertEquals(0, MarkLogicReSTApiClientProvider.getTripleCount());
    }
}
