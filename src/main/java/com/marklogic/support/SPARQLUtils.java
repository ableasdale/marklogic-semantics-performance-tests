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

    public static void deleteAllTriples(MarkLogicRepositoryConnection conn) {
        try {
            conn.prepareUpdate("CLEAR ALL").execute();
        } catch (RepositoryException | UpdateExecutionException | MalformedQueryException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isDatabaseEmpty(MarkLogicRepositoryConnection conn) {
        return countAllTriples(conn).equals("0");
    }

    public static String countAllTriples(MarkLogicRepositoryConnection conn) {
        try {
            String query = "select (count(*) as ?total) where { ?s ?p ?o . }";
            MarkLogicTupleQuery tupleQuery = conn.prepareTupleQuery(query);
            TupleQueryResult result = tupleQuery.evaluate();
            return result.next().getBinding("total").getValue().stringValue();
        } catch (RepositoryException | QueryEvaluationException | MalformedQueryException e) {
            throw new RuntimeException(e);
        }
    }
}
