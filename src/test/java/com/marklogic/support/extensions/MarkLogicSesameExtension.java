package com.marklogic.support.extensions;

import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.providers.MarkLogicSesameRepositoryProvider;
import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by ableasdale on 28/05/2017.
 */
public class MarkLogicSesameExtension implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback, Extension {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @Override
    public void beforeAll(ContainerExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE ALL TESTS)", MethodHandles.lookup().lookupClass().getSimpleName()));
        assertTrue(MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection().isOpen());
        assertTrue(MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection().isEmpty());
        assertTrue(MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection().size() == 0);
    }

    @Override
    public void afterAll(ContainerExtensionContext context) throws Exception {
        LOG.info("MARKLOGIC Sesame: AFTER ALL :)");
    }

    @Override
    public void afterTestExecution(TestExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (AFTER TEST) :: Clearing all triples", MethodHandles.lookup().lookupClass().getSimpleName()));
        SPARQLUtils.deleteAllTriples(MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection());
    }

    @Override
    public void beforeTestExecution(TestExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE TEST)", MethodHandles.lookup().lookupClass().getSimpleName()));
        assertTrue(SPARQLUtils.isDatabaseEmpty(MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection()));
    }
}
