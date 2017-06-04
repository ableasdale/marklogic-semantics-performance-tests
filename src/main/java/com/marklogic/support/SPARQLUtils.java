package com.marklogic.support;

import com.marklogic.semantics.jena.MarkLogicDatasetGraph;
import com.marklogic.semantics.sesame.MarkLogicRepositoryConnection;
import com.marklogic.semantics.sesame.query.MarkLogicTupleQuery;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.UpdateExecutionException;
import org.openrdf.repository.RepositoryException;

/**
 * Created by ableasdale on 01/06/2017.
 */
public class SPARQLUtils {

    private static final String SELECT_ALL = "select (count(*) as ?total) where { ?s ?p ?o . }";
    private static final String CLEAR_ALL = "CLEAR ALL";

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
}