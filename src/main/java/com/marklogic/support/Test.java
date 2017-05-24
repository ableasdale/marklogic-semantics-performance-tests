package com.marklogic.support;

import com.marklogic.semantics.sesame.MarkLogicRepository;
import com.marklogic.semantics.sesame.MarkLogicRepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
import org.rdfhdt.hdt.exceptions.NotFoundException;
import org.rdfhdt.hdt.hdt.HDT;
import org.rdfhdt.hdt.hdt.HDTManager;
import org.rdfhdt.hdt.triples.IteratorTripleString;
import org.rdfhdt.hdt.triples.TripleString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.invoke.MethodHandles;

/**
 * Created by ableasdale on 24/05/2017.
 */
public class Test {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) throws FileNotFoundException {

        //MarkLogicRepositoryConnection conn = Repository.getMarkLogicRepositoryConnection();

        //LOG.info("active"+conn.isActive());


        try {
            MarkLogicRepository repo = new MarkLogicRepository(Configuration.HOST, Configuration.PORT, Configuration.USERNAME, Configuration.PASSWORD, Configuration.AUTH);
            repo.initialize();
            MarkLogicRepositoryConnection conn = repo.getConnection();
           //HDT hdt = HDTManager.loadHDT("src/main/resources/wordnet-2013-03-20.hdt", null);

//            IteratorTripleString it = hdt.search("", "", "");
//            while(it.hasNext()) {
//                TripleString ts = it.next();
//                conn.add(ts);
//                //LOG.info(""+ts);
//            }

            conn.add(Utils.getFileReader("country-records.ttl"), "", RDFFormat.TURTLE);
        } catch (IOException e) {
            LOG.error("File I/O Exception encountered: ", e);
        } catch (RDFParseException e) {
            LOG.error("Issue parsing RDF File encountered: ", e);
        } catch (RepositoryException e) {
            LOG.error("Issue connecting to the repository encountered: ", e);
        }
        LOG.info("Here");
    }
}
