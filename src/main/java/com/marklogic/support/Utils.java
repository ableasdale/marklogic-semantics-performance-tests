package com.marklogic.support;

import org.apache.commons.io.FileUtils;
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

    public static Reader getFileReader(String name) {
        try {
            File f = new File(MethodHandles.lookup().lookupClass().getClassLoader().getResource(name).getFile());
            FileReader fr = new FileReader(f);
            /*fr. URL url = FileSizeBench.class
                    .getResource("FileSizeBench.class");
            stream = url.openStream();
            return stream.available(); */
            LOG.info(String.format("File name: %s | File length: %s", f.getName(), FileUtils.byteCountToDisplaySize(f.length())));
            return fr;
        } catch (FileNotFoundException e) {
            LOG.error(String.format("Unable to open file %s", name), e);
        }
        return null;
    }

}
