package com.marklogic.support.providers;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.support.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Created by ableasdale on 29/05/2017.
 */
public class MarkLogicJavaClientProvider {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private DatabaseClient client;

    private MarkLogicJavaClientProvider() {
        client = DatabaseClientFactory.newClient(
                Configuration.HOST, Configuration.PORT, Configuration.DATABASE,
                new DatabaseClientFactory.DigestAuthContext(Configuration.USERNAME, Configuration.PASSWORD));
    }

    private static MarkLogicJavaClientProvider getInstance() {
        return LazyHolder.INSTANCE;
    }

    public static DatabaseClient getClient() {
        return getInstance().client;
    }

    private static class LazyHolder {
        static final MarkLogicJavaClientProvider INSTANCE = new MarkLogicJavaClientProvider();
    }

}
