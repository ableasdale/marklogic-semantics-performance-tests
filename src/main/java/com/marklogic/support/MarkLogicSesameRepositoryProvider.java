package com.marklogic.support;

import com.marklogic.semantics.sesame.MarkLogicRepository;
import com.marklogic.semantics.sesame.MarkLogicRepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Created by ableasdale on 24/05/2017.
 */
public class MarkLogicSesameRepositoryProvider {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private MarkLogicRepository markLogicSesameApiConnection;

    private MarkLogicSesameRepositoryProvider() {
        LOG.info("Creating MarkLogic MarkLogicSesameRepositoryProvider");
        markLogicSesameApiConnection = new MarkLogicRepository(Configuration.HOST, Configuration.PORT, Configuration.USERNAME, Configuration.PASSWORD, Configuration.AUTH);
        try {
            markLogicSesameApiConnection.initialize();
            LOG.info("MarkLogicSesameRepositoryProvider should now be set up");
        } catch (RepositoryException e) {
            LOG.error("Unable to connect to Sesame API", e);
        }
    }

    private static class LazyHolder {
        static final MarkLogicSesameRepositoryProvider INSTANCE = new MarkLogicSesameRepositoryProvider();
    }


    private static MarkLogicSesameRepositoryProvider getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static MarkLogicRepository getMarkLogicRepository() {
        LOG.debug("Getting Sesame MarkLogicSesameRepositoryProvider");
        return MarkLogicSesameRepositoryProvider.getInstance().markLogicSesameApiConnection;
    }

    public static MarkLogicRepositoryConnection getMarkLogicRepositoryConnection() {
        LOG.info("Getting MarkLogic Sesame Repository Connection");
        try {
            return MarkLogicSesameRepositoryProvider.getInstance().getMarkLogicRepository().getConnection();
        } catch (RepositoryException e) {
            LOG.error("Unable to create Sesame API MarkLogicSesameRepositoryProvider connection", e);
        }
        return null;
    }

}
