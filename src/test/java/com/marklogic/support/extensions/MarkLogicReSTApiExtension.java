package com.marklogic.support.extensions;

import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ableasdale on 06/06/2017.
 */
public class MarkLogicReSTApiExtension implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback, Extension {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (AFTER ALL TESTS)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info("■ MarkLogic ReST API Client (AFTER ALL TESTS) ■");
        assertEquals(200, MarkLogicReSTApiClientProvider.createGetForValidationCheck().code());
        assertEquals(200, MarkLogicReSTApiClientProvider.createPostForClearingDatabase().code());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (AFTER TEST) :: Clearing all triples", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info(String.format(" ■■ MarkLogic ReST API Client (AFTER) ■ %s ■■", context.getDisplayName()));
        assertEquals(200, MarkLogicReSTApiClientProvider.createGetForValidationCheck().code());
        assertEquals(200, MarkLogicReSTApiClientProvider.createPostForClearingDatabase().code());
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE ALL TESTS)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info("■ MarkLogic ReST API Client (BEFORE ALL TESTS) ■");
        assertEquals(200, MarkLogicReSTApiClientProvider.createGetForValidationCheck().code());
        assertEquals(0, MarkLogicReSTApiClientProvider.getTripleCount());
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE TEST)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info(String.format(" ■■ MarkLogic ReST API Client (BEFORE) ■ %s ■■", context.getDisplayName()));
        assertEquals(0, MarkLogicReSTApiClientProvider.getTripleCount());
    }
}
