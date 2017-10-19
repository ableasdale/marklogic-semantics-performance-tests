package com.marklogic.support.extensions;

import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ableasdale on 28/05/2017.
 */
public class MarkLogicExtension implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback, Extension {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public MarkLogicExtension() {
        LOG.debug(String.format("%s (CONSTRUCTOR)", MethodHandles.lookup().lookupClass().getSimpleName()));
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE ALL TESTS)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info("■ MarkLogic XQuery (BEFORE ALL TESTS) ■");
        assertEquals(0, MarkLogicReSTApiClientProvider.getTripleCount());
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (AFTER ALL TESTS)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info("■ MarkLogic XQuery (AFTER ALL TESTS) ■");
        assertEquals(200, MarkLogicReSTApiClientProvider.createPostForClearingDatabase().code());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (AFTER TEST)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info(String.format(" ■■ MarkLogic XQuery (AFTER) ■ %s ■■", context.getDisplayName()));
        assertEquals(200, MarkLogicReSTApiClientProvider.createPostForClearingDatabase().code());
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE TEST)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info(String.format(" ■■ MarkLogic XQuery (BEFORE) ■ %s ■■", context.getDisplayName()));
        assertEquals(0, MarkLogicReSTApiClientProvider.getTripleCount());
    }
}
