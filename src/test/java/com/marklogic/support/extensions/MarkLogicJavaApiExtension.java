package com.marklogic.support.extensions;

import com.marklogic.client.util.RequestLogger;
import com.marklogic.support.providers.MarkLogicReSTApiClientProvider;
import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;
import java.lang.invoke.MethodHandles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ableasdale on 29/05/2017.
 */
public class MarkLogicJavaApiExtension implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback, Extension {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (AFTER ALL TESTS)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info("■ MarkLogic Java Client (AFTER ALL TESTS) ■");
        assertEquals(200, MarkLogicReSTApiClientProvider.createPostForClearingDatabase().getStatus());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (AFTER TEST)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info(String.format(" ■■ MarkLogic Java Client (AFTER) ■ %s ■■", context.getDisplayName()));
        assertEquals(200, MarkLogicReSTApiClientProvider.createPostForClearingDatabase().getStatus());
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE ALL TESTS)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info("■ MarkLogic Java Client (BEFORE ALL TESTS) ■");

        /*
        DatabaseClient dc  = MarkLogicJavaClientProvider.getClient();
        DocumentManager dm = dc.newDocumentManager();
        RequestLogger reqlog = dc.newLogger(System.out);

        dm.startLogging(reqlog);
        PrintStream out = reqlog.getPrintStream();
        out.println("HELLO1!");
        out.flush();

        // stop logging
        dm.stopLogging();




        RequestLogger reqlog1 = MarkLogicJavaClientProvider.getClient().newLogger(System.out);

        reqlog.setContentMax(RequestLogger.ALL_CONTENT);
        PrintStream out1 = reqlog1.getPrintStream();
        out1.println("HELLO!");
        out1.flush();

        logRequest(MarkLogicJavaClientProvider.getClient().newLogger(System.out), "hello\n");
        //logRequest(reqlog, "deleted %s document", "FOFO");
        //MarkLogicJavaClientProvider.getClient().newDocumentManager().
                //getPointInTimeQueryTimestamp();  getServerTimestamp */
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        LOG.debug(String.format("%s (BEFORE TEST)", MethodHandles.lookup().lookupClass().getSimpleName()));
        LOG.info(String.format(" ■■ MarkLogic Java Client (BEFORE) ■ %s ■■", context.getDisplayName()));
    }

    // TODO - why can't i get this to log anything?!?  Is this recently broken in ML9?!?

    private void logRequest(RequestLogger reqlog, String message,
                            Object... params) {
        if (reqlog == null)
            return;

        PrintStream out = reqlog.getPrintStream();
        if (out == null)
            return;

        if (params == null || params.length == 0) {
            out.println(message);
        } else {
            out.format(message, params);
            out.println();
        }
    }
}
