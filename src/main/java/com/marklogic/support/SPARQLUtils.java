package com.marklogic.support;

import com.marklogic.semantics.sesame.MarkLogicRepositoryConnection;
import com.marklogic.semantics.sesame.query.MarkLogicTupleQuery;
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

    public static int countAllTriples(MarkLogicRepositoryConnection conn) {
        try {
            MarkLogicTupleQuery tupleQuery = conn.prepareTupleQuery(SELECT_ALL);
            TupleQueryResult result = tupleQuery.evaluate();
            return Integer.parseInt(result.next().getBinding("total").getValue().stringValue());
        } catch (RepositoryException | QueryEvaluationException | MalformedQueryException e) {
            throw new RuntimeException(e);
        }
    }
}
