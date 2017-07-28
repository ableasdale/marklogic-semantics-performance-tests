package com.marklogic.support;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.io.StringHandle;
import com.marklogic.client.semantics.SPARQLMimeTypes;
import com.marklogic.client.semantics.SPARQLQueryManager;
import com.marklogic.semantics.jena.MarkLogicDatasetGraph;
import com.marklogic.semantics.rdf4j.MarkLogicRepositoryConnection;
import com.marklogic.semantics.rdf4j.query.MarkLogicTupleQuery;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.eclipse.rdf4j.query.MalformedQueryException;
import org.eclipse.rdf4j.query.QueryEvaluationException;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.query.UpdateExecutionException;
import org.eclipse.rdf4j.repository.RepositoryException;

/**
 * Created by ableasdale on 01/06/2017.
 */
public class SPARQLUtils {

    // private static final String SELECT_ALL_SHORT = "select count(*) where { ?s ?p ?o . }";
    private static final String SELECT_ALL = "select (count(*) as ?total) where { ?s ?p ?o . }";
    private static final String CLEAR_ALL = "CLEAR ALL";
    private static final String GRAPH_QUERY = "select (count(?g) as ?count) { graph ?g {} }";

    public static void deleteAllTriples(MarkLogicRepositoryConnection conn) {
        try {
            conn.prepareUpdate(CLEAR_ALL).execute();
        } catch (RepositoryException | UpdateExecutionException | MalformedQueryException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isDatabaseEmpty(MarkLogicRepositoryConnection conn) {
        return countAllTriples(conn) == 0;
    }

    /**
     * Count all Triples using the Sesame MarkLogicRepositoryConnection
     *
     * @param conn
     * @return
     */
    public static int countAllTriples(MarkLogicRepositoryConnection conn) {
        try {
            MarkLogicTupleQuery tupleQuery = conn.prepareTupleQuery(SELECT_ALL);
            TupleQueryResult result = tupleQuery.evaluate();
            return Integer.parseInt(result.next().getBinding("total").getValue().stringValue());
        } catch (RepositoryException | QueryEvaluationException | MalformedQueryException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Count all triples using the Jena Client API MarkLogicDatasetGraph
     *
     * @param dsg
     * @return
     */
    public static int countAllTriples(MarkLogicDatasetGraph dsg) {

        QueryExecution execution = QueryExecutionFactory.create(SELECT_ALL, dsg.toDataset());
        // TODO - this is a little janky right now - can this be rewritten to make it less messy?
        int n = 1;
        for (ResultSet results = execution.execSelect();
             results.hasNext();
             n++) {
            QuerySolution solution = results.next();
            return Integer.parseInt(
                    solution.get("total").asLiteral().getString()
            );

        }
        return 0;
    }

    /**
     * Delete all triples using the Jena Client API MarkLogicDatasetGraph
     *
     * @param dsg
     */
    public static void deleteAllTriples(MarkLogicDatasetGraph dsg) {
        UpdateRequest update = UpdateFactory.create(CLEAR_ALL);
        UpdateProcessor processor = UpdateExecutionFactory.create(update, dsg);
        processor.execute();
    }

    public static boolean isDatabaseEmpty(MarkLogicDatasetGraph dsg) {
        return countAllTriples(dsg) == 0;
    }

    /**
     * Count all triples using the MarkLogic Java Client API
     *
     * @param client
     * @return
     */
    public static int countAllTriples(DatabaseClient client) {
        SPARQLQueryManager sparqlMgr = client.newSPARQLQueryManager();
        // TODO - works for now; but there *MUST* be a better way to do this??
        // .newRowManager() perhaps?
        String total = sparqlMgr.executeSelect(
                sparqlMgr.newQueryDefinition(SELECT_ALL),
                new StringHandle().withMimetype(SPARQLMimeTypes.SPARQL_CSV)
        ).get().split(System.lineSeparator())[1];

        return Integer.parseInt(total);
    }
}