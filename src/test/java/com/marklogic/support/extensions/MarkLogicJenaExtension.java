package com.marklogic.support.extensions;

import com.marklogic.semantics.jena.MarkLogicDatasetGraph;
import com.marklogic.semantics.jena.MarkLogicDatasetGraphFactory;
import com.marklogic.support.util.SPARQLUtils;
import com.marklogic.support.providers.MarkLogicJavaClientProvider;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by ableasdale on 29/05/2017.
 */
public class MarkLogicJenaExtension implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback, Extension {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    // TODO - should this be handled by a provider rather than referenced directly in tests?
    public static final MarkLogicDatasetGraph DSG = MarkLogicDatasetGraphFactory.createDatasetGraph(MarkLogicJavaClientProvider.getClient());

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (AFTER ALL TESTS)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info("■ MarkLogic Jena Client (AFTER ALL TESTS) ■");
        assertEquals(200, MarkLogicReSTApiClientProvider.createPostForClearingDatabase().code());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (AFTER TEST) :: Clearing all triples", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info(String.format(" ■■ MarkLogic Jena Client (AFTER) ■ %s ■■", context.getDisplayName()));
        //SPARQLUtils.deleteAllTriples(DSG);
        // assertTrue(DSG.size() == 0);
        //DSG.close();
        //DSG = null;
        assertEquals(200, MarkLogicReSTApiClientProvider.createPostForClearingDatabase().code());
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE ALL TESTS)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info("■ MarkLogic Jena Client (BEFORE ALL TESTS) ■");
        assertTrue(SPARQLUtils.isDatabaseEmpty(DSG));
        assertEquals(0, MarkLogicReSTApiClientProvider.getTripleCount());
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE TEST)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info(String.format(" ■■ MarkLogic Jena Client (BEFORE) ■ %s ■■", context.getDisplayName()));
        assertEquals(0, MarkLogicReSTApiClientProvider.getTripleCount());
    }
}
