package com.marklogic.support;

import org.junit.jupiter.api.extension.Extension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Created by ableasdale on 28/05/2017.
 */
public class MarkLogicExtension implements Extension {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public MarkLogicExtension(){
        LOG.info("MarkLogic Extension here..");
    }
}
