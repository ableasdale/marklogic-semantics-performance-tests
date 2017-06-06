package com.marklogic.support.extensions;

import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Created by ableasdale on 06/06/2017.
 */
public class MarkLogicReSTApiExtension  implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback, Extension {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void afterAll(ContainerExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (AFTER ALL TESTS)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info("■ MarkLogic ReST API Client (AFTER ALL TESTS) ■");

    }

    @Override
    public void afterTestExecution(TestExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (AFTER TEST) :: Clearing all triples", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info(String.format(" ■■ MarkLogic ReST API Client (AFTER) ■ %s ■■", context.getDisplayName()));
    }

    @Override
    public void beforeAll(ContainerExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE ALL TESTS)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info("■ MarkLogic ReST API Client (BEFORE ALL TESTS) ■");
    }

    @Override
    public void beforeTestExecution(TestExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE TEST)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info(String.format(" ■■ Sesame ReST API Client (BEFORE) ■ %s ■■", context.getDisplayName()));
    }
}
