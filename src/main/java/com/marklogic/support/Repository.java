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
public class Repository {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private MarkLogicRepository markLogicSesameApiConnection;

    private Repository() {
        LOG.info("Creating MarkLogic Repository connection");
        markLogicSesameApiConnection = new MarkLogicRepository(Configuration.HOST, Configuration.PORT, Configuration.USERNAME, Configuration.PASSWORD, Configuration.AUTH);
        try {
            markLogicSesameApiConnection.initialize();
            LOG.info("Repository should now be set up");
        } catch (RepositoryException e) {
            LOG.error("Unable to connect to Sesame API", e);
        }
    }

    private static class LazyHolder {
        static final Repository INSTANCE = new Repository();
    }


    private static Repository getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static MarkLogicRepository getMarkLogicRepository() {
        LOG.info("Getting Sesame Repository");
        return Repository.getInstance().markLogicSesameApiConnection;
    }

    public static MarkLogicRepositoryConnection getMarkLogicRepositoryConnection() {
        LOG.info("Getting Sesame Connection");
        try {
            Repository.getInstance().getMarkLogicRepository().getConnection();
        } catch (RepositoryException e) {
            LOG.error("Unable to create Sesame API Repository connection", e);
        }
        return null;
    }

}
