package com.marklogic.support;

import com.marklogic.semantics.rdf4j.MarkLogicRepository;
import com.marklogic.semantics.rdf4j.MarkLogicRepositoryConnection;
import com.marklogic.support.util.FileUtils;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

/**
 * Created by ableasdale on 24/05/2017.
 */
public class Test {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) throws FileNotFoundException {

        //MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

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


            // BAD conn.add(FileUtils.getFileReader("fulldump.ttl"), "", RDFFormat.TURTLE);
            // BAD conn.add(FileUtils.getFileReader("images_en.ttl"), "", RDFFormat.TURTLE);

            // N3
            // GOOD conn.add(FileUtils.getFileReader("sec.n3"), "", RDFFormat.N3);
            // BAD - TOOBIG conn.add(FileUtils.getFileReader("chemogenomics.n3"), "", RDFFormat.N3);


            // NQUADS
            // GOOD conn.add(FileUtils.getFileReader("sider-indications_raw.nq"), "", RDFFormat.NQUADS);
            // GOOD conn.add(FileUtils.getFileReader("sider-adverse_effects_raw.nq"), "", RDFFormat.NQUADS);
            conn.add(FileUtils.getFileReader("sider-label_mapping.nq"), "", RDFFormat.NQUADS);


            // BAD conn.add(FileUtils.getFileReader("625KGeologyMap_Dyke.nt"), "", RDFFormat.NTRIPLES);

            // TURTLE
            // GOOD conn.add(FileUtils.getFileReader("turtle/void.ttl"), "", RDFFormat.TURTLE);
            // GOOD conn.add(FileUtils.getFileReader("turtle/IVOAT.ttl"), "", RDFFormat.TURTLE);
            // GOOD conn.add(FileUtils.getFileReader("turtle/country-records.ttl"), "", RDFFormat.TURTLE);
            // GOOD conn.add(FileUtils.getFileReader("turtle/countries.ttl"), "", RDFFormat.TURTLE);
            // GOOD conn.add(FileUtils.getFileReader("turtle/history.ttl"), "", RDFFormat.TURTLE);
            // GOOD conn.add(FileUtils.getFileReader("turtle/units.ttl"), "", RDFFormat.TURTLE);
            // GOOD - 984MB - takes ~10 minutes conn.add(FileUtils.getFileReader("turtle/names.ttl"), "", RDFFormat.TURTLE);
            // BAD - TOOBIG conn.add(FileUtils.getFileReader("turtle/features.ttl"), "", RDFFormat.TURTLE);


            // RDFXML
            // GOOD conn.add(FileUtils.getFileReader("rdfxml/currencies.rdf"), "", RDFFormat.RDFXML);
            // GOOD conn.add(FileUtils.getFileReader("rdfxml/continents.rdf"), "", RDFFormat.RDFXML);
            // GOOD conn.add(FileUtils.getFileReader("rdfxml/countries.rdf"), "", RDFFormat.RDFXML);
            // GOOD conn.add(FileUtils.getFileReader("rdfxml/capitals.rdf"), "", RDFFormat.RDFXML);
            // BAD conn.add(FileUtils.getFileReader("rdfxml/geospecies.rdf"), "", RDFFormat.RDFXML);


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
