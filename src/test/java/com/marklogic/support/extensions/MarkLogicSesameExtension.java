package com.marklogic.support.extensions;

import com.marklogic.semantics.sesame.MarkLogicRepositoryConnection;
import com.marklogic.semantics.sesame.query.MarkLogicUpdateQuery;
import com.marklogic.support.providers.MarkLogicSesameRepositoryProvider;
import org.junit.jupiter.api.extension.*;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.UpdateExecutionException;
import org.openrdf.repository.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by ableasdale on 28/05/2017.
 */
public class MarkLogicSesameExtension implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback, Extension {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static void deleteAllTriples(MarkLogicRepositoryConnection conn) {
        try {
            conn.prepareUpdate("DROP ALL").execute();
        } catch (RepositoryException | UpdateExecutionException | MalformedQueryException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void beforeAll(ContainerExtensionContext context) throws Exception {
        LOG.info("MARKLOGIC Sesame: BEFORE ALL :)" +  MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection().isActive());
        assertTrue(MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection().isOpen());
    }

    @Override
    public void afterAll(ContainerExtensionContext context) throws Exception {
        LOG.info("MARKLOGIC Sesame: AFTER ALL :)");
    }

    @Override
    public void afterTestExecution(TestExtensionContext context) throws Exception {
        LOG.info("MARKLOGIC Sesame: AFTER TEST :)");
        deleteAllTriples(MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection());
    }

    @Override
    public void beforeTestExecution(TestExtensionContext context) throws Exception {
        LOG.info("MARKLOGIC Sesame: BEFORE TEST :)");
    }
}
