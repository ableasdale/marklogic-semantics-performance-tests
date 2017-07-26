package com.marklogic.support.providers;

import com.marklogic.semantics.rdf4j.MarkLogicRepository;
import com.marklogic.semantics.rdf4j.MarkLogicRepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Created by ableasdale on 24/05/2017.
 */
public class MarkLogicRDF4JRepositoryProvider {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private MarkLogicRepository markLogicRepository;

    private MarkLogicRDF4JRepositoryProvider() {
        markLogicRepository = new MarkLogicRepository(MarkLogicJavaClientProvider.getClient());
        try {
            markLogicRepository.initialize();
            LOG.debug("MarkLogicRDF4JRepositoryProvider should now be set up");
        } catch (RepositoryException e) {
            LOG.error("Unable to connect to Sesame API", e);
        }
    }

    private static class LazyHolder {
        static final MarkLogicRDF4JRepositoryProvider INSTANCE = new MarkLogicRDF4JRepositoryProvider();
    }

    private static MarkLogicRDF4JRepositoryProvider getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static MarkLogicRepository getMarkLogicRepository() {
        LOG.debug("Getting Sesame MarkLogicRDF4JRepositoryProvider");
        return MarkLogicRDF4JRepositoryProvider.getInstance().markLogicRepository;
    }

    public static MarkLogicRepositoryConnection getMarkLogicRepositoryConnection() {
        LOG.debug("Getting MarkLogic Sesame Repository Connection");
        try {
            return MarkLogicRDF4JRepositoryProvider.getInstance().getMarkLogicRepository().getConnection();
        } catch (RepositoryException e) {
            LOG.error("Unable to create Sesame API MarkLogicRDF4JRepositoryProvider connection", e);
        }
        return null;
    }

}
