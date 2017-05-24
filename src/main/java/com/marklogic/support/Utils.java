package com.marklogic.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.invoke.MethodHandles;

/**
 * Created by ableasdale on 24/05/2017.
 */
public class Utils {

    private static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    protected static Reader getFileReader(String name) {
        try {
            return new FileReader(new File(MethodHandles.lookup().lookupClass().getClassLoader().getResource(name).getFile()));
        } catch (FileNotFoundException e) {
            LOG.error(String.format("Unable to open file %s", name), e);
        }
        return null;
    }

}
