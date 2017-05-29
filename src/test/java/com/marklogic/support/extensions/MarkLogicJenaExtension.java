package com.marklogic.support.extensions;

import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Created by ableasdale on 29/05/2017.
 */
public class MarkLogicJenaExtension implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback, Extension {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void afterAll(ContainerExtensionContext context) throws Exception {
        LOG.info("MARKLOGIC Jena Client: AFTER ALL");
    }

    @Override
    public void afterTestExecution(TestExtensionContext context) throws Exception {
        LOG.info("MARKLOGIC Jena Client: AFTER Test");
    }

    @Override
    public void beforeAll(ContainerExtensionContext context) throws Exception {
        LOG.info("MARKLOGIC Jena Client: BEFORE ALL");
    }

    @Override
    public void beforeTestExecution(TestExtensionContext context) throws Exception {
        LOG.info("MARKLOGIC Jena Client: Before test");
    }
}
