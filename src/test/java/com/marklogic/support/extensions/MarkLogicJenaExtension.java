package com.marklogic.support.extensions;

import com.marklogic.semantics.jena.MarkLogicDatasetGraph;
import com.marklogic.semantics.jena.MarkLogicDatasetGraphFactory;
import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.providers.MarkLogicJavaClientProvider;
import com.marklogic.support.providers.MarkLogicSesameRepositoryProvider;
import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by ableasdale on 29/05/2017.
 */
public class MarkLogicJenaExtension implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback, Extension {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static MarkLogicDatasetGraph DSG = null;

    @Override
    public void afterAll(ContainerExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (AFTER ALL TESTS)", MethodHandles.lookup().lookupClass().getSimpleName()));
    }

    @Override
    public void afterTestExecution(TestExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (AFTER TEST) :: Clearing all triples", MethodHandles.lookup().lookupClass().getSimpleName()));
        SPARQLUtils.deleteAllTriples(DSG);
        // assertTrue(DSG.size() == 0);
        DSG.close();
        DSG = null;
    }

    @Override
    public void beforeAll(ContainerExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE ALL TESTS)", MethodHandles.lookup().lookupClass().getSimpleName()));
        /*DSG = MarkLogicDatasetGraphFactory
                .createDatasetGraph(MarkLogicJavaClientProvider.getClient());
        */
        // TODO - check connection
    }

    @Override
    public void beforeTestExecution(TestExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE TEST)", MethodHandles.lookup().lookupClass().getSimpleName()));
        DSG = MarkLogicDatasetGraphFactory
                .createDatasetGraph(MarkLogicJavaClientProvider.getClient());
        assertTrue(SPARQLUtils.isDatabaseEmpty(DSG));
    }
}
