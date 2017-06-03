package com.marklogic.support.extensions;

import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Created by ableasdale on 28/05/2017.
 */
public class MarkLogicExtension implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback, Extension {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public MarkLogicExtension() {
        LOG.debug(String.format("%s (CONSTRUCTOR)", MethodHandles.lookup().lookupClass().getSimpleName()));
    }

    @Override
    public void beforeAll(ContainerExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE ALL TESTS)", MethodHandles.lookup().lookupClass().getSimpleName()));
    }

    @Override
    public void afterAll(ContainerExtensionContext context) throws Exception {
        LOG.info("MARKLOGIC: AFTER ALL :)");
    }

    @Override
    public void afterTestExecution(TestExtensionContext context) throws Exception {
        LOG.info("MARKLOGIC: AFTER TEST :)");
    }

    @Override
    public void beforeTestExecution(TestExtensionContext context) throws Exception {
        LOG.info("MARKLOGIC: BEFORE TEST :)");
    }
}
