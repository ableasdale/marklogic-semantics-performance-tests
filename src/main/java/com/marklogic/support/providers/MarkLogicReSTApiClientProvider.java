package com.marklogic.support.providers;

import com.burgstaller.okhttp.AuthenticationCacheInterceptor;
import com.burgstaller.okhttp.CachingAuthenticatorDecorator;
import com.burgstaller.okhttp.digest.CachingAuthenticator;
import com.burgstaller.okhttp.digest.Credentials;
import com.burgstaller.okhttp.digest.DigestAuthenticator;
import com.marklogic.support.util.Configuration;
import com.marklogic.support.util.SPARQLUtils;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by ableasdale on 06/06/2017.
 */
public class MarkLogicReSTApiClientProvider {

    private static final DigestAuthenticator authenticator = new DigestAuthenticator(new Credentials(Configuration.USERNAME, Configuration.PASSWORD));
    private static final Map<String, CachingAuthenticator> authCache = new ConcurrentHashMap<>();

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.MINUTES)
            .writeTimeout(90, TimeUnit.MINUTES)
            .readTimeout(90, TimeUnit.MINUTES)
            .authenticator(new CachingAuthenticatorDecorator(authenticator, authCache))
            .addInterceptor(new AuthenticationCacheInterceptor(authCache))
            .build();

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String SPARQL_ENDPOINT = String.format("http://%s:%d/v1/graphs/sparql?database=%s", Configuration.HOST, Configuration.PORT, Configuration.DATABASE);
    private static final String EVAL = String.format("http://%s:%d/v1/eval?database=%s", Configuration.HOST, Configuration.PORT, Configuration.DATABASE);

    // See: https://docs.marklogic.com/guide/semantics/loading#id_70682 for full list
    private static final String DEFAULT_GRAPH = String.format("http://%s:%d/v1/graphs?default&database=%s", Configuration.HOST, Configuration.PORT, Configuration.DATABASE);
    private static final String UNSPECIFIED_GRAPH = String.format("http://%s:%d/v1/graphs?database=%s", Configuration.HOST, Configuration.PORT, Configuration.DATABASE);
    private static final String LATEST_REST_APIS = String.format("http://%s:%d/LATEST/rest-apis", Configuration.HOST, 8002);

    private static final String URLENCODED_FORM_MIMETYPE = "application/x-www-form-urlencoded";
    private static final String TURTLE_MIMETYPE = "application/x-turtle";
    private static final String NQUAD_MIMETYPE = "application/n-quads";
    private static final String NTRIPLES_MIMETYPE = "application/n-triples";
    //text/plain
    private static final String N3_MIMETYPE = "text/n3";
    private static final String TRIG_MIMETYPE = "application/trig";
    private static final String RDFXML_MIMETYPE = "application/rdf+xml";

    public static String createXQuerySemLoad(String filename, String type) {
        return String.format("xquery=xquery version \"1.0-ml\"; \nimport module namespace sem = \"http://marklogic.com/semantics\" at \"/MarkLogic/semantics.xqy\";\nsem:rdf-load('%s/%s%s', \"%s\")", System.getProperty("user.dir"), Configuration.RESOURCES, filename, type);
    }

    public static Response createPostForClearingDatabase() {
        Request request = new Request.Builder()
                .url(String.format("http://localhost:8002/manage/v2/forests/%s", Configuration.FOREST))
                .post(RequestBody.create(MediaType.parse(URLENCODED_FORM_MIMETYPE), "state=clear"))
                .build();
        try {
            Response response = client.newCall(request).execute();
            LOG.debug(String.format("Cleared Database :: Client Response Status: %d", response.code()));
            return response;
        } catch (IOException e) {
            LOG.error("Exception caught creating resource: ", e);
        }
        return null;
    }


    public static Response createGetForValidationCheck() {
        Request request = new Request.Builder()
                .url(LATEST_REST_APIS)
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();

            //WebResource wr = getConfiguredInstance().resource(LATEST_REST_APIS);
            //Response response = wr.type("application/json").get(ClientResponse.class);
            LOG.debug(String.format("Client Response Status: %d", response.code()));
            return response;

        } catch (IOException e) {
            LOG.error("Exception caught creating resource: ", e);
        }
        return null;
    }


    public static int getTripleCount() {
        Request request = new Request.Builder()
                .url(SPARQL_ENDPOINT)
                .header("Accept", "text/csv")
                .post(RequestBody.create(MediaType.parse("application/sparql-query"), SPARQLUtils.SELECT_COUNT))
                .build();
        try {
            Response response = client.newCall(request).execute();

            String total = response.body().string();
            LOG.debug(String.format("Client Response Status: %d || Client Response in full: %s", response.code(), total));
            //LOG.info("TOTAL: +"+Integer.parseInt(total.split(System.lineSeparator())[1]));
            return Integer.parseInt(total.split(System.lineSeparator())[1]);
        } catch (IOException e) {
            LOG.error("Exception caught creating resource: ", e);
        }
        return 0;
    }


    public static int getGraphCount() {
        Request request = new Request.Builder()
                .url(SPARQL_ENDPOINT)
                .header("Accept", "text/csv")
                .post(RequestBody.create(MediaType.parse("application/sparql-query"), "select (count(?g) as ?count) { graph ?g {} }"))
                .build();
        try {
            Response response = client.newCall(request).execute();
            String total = response.body().string();
            LOG.debug(String.format("Client Response Status: %d || Client Response in full: %s", response.code(), total));
            //LOG.info("TOTAL: +"+Integer.parseInt(total.split(System.lineSeparator())[1]));
            return Integer.parseInt(total.split(System.lineSeparator())[1]);
        } catch (IOException e) {
            LOG.error("Exception caught creating resource: ", e);
        }
        return 0;
    }

    /*
    TODO - not quite right!
    public static Response getTripleCountAsEval() {
        //WebResource wr = getConfiguredInstance().resource(EVAL);
        //Response response = wr.type("application/x-www-form-urlencoded").header("Accept", "multipart/mixed; boundary=BOUNDARY").post(ClientResponse.class, "xquery=sem:sparql(select (count(*) as ?total) where { ?s ?p ?o . })");

        Request request = new Request.Builder()
                .url(EVAL.toASCIIString())
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();

            LOG.debug("Client Response Status: " + response.code());
            //response.getEntity().getContent();
            LOG.debug("Client Response in full: " + response.body().string());
            return response;
        } catch (IOException e) {
            LOG.error("Exception caught creating resource: ", e);
        }
        return null;
    } */


    public static Response evalXQuery(String xquery) {

        Request request = new Request.Builder()
                .url(EVAL)
                .header("Accept", "multipart/mixed; boundary=BOUNDARY")
                .post(RequestBody.create(MediaType.parse(URLENCODED_FORM_MIMETYPE), xquery))
                .build();

        try {
            Response response = client.newCall(request).execute();
            LOG.debug(String.format("Client Response Status: %d", response.code()));
            LOG.debug(String.format("Client Response in full: %s", response.body().string()));
            return response;
        } catch (IOException e) {
            LOG.error("Exception caught creating resource: ", e);
        }
        return null;
    }

    private static Response createPost(String filename, String mimetype, boolean default_graph) {
        String uri = default_graph ? DEFAULT_GRAPH : UNSPECIFIED_GRAPH;

        LOG.debug(String.format("Mimetype: %s", mimetype));
        LOG.debug(String.format("URI: %s", uri));

        try {
            Request request = new Request.Builder()
                    .url(uri)
                    .post(RequestBody.create(MediaType.parse(mimetype), new String(Files.readAllBytes(Paths.get(String.format("%s%s", Configuration.RESOURCES, filename))))))
                    .build();
            Response response = client.newCall(request).execute();

            String res = response.body().string();
            //LOG.debug(String.format("String: %s", new String(Files.readAllBytes(Paths.get(String.format("%s%s", Configuration.RESOURCES, filename))))));
            //LOG.debug(String.format("Response length: %d", response.getLength()));
            LOG.debug(String.format("Client Response Status: %d", response.code()));
            LOG.debug(String.format("Client Response: %s", res));
            return response;
        } catch (IOException e) {
            LOG.error("Exception caught creating resource: ", e);
        }
        return null;

    }

    private static Response createPost(String filename, String mimetype) {
        return createPost(filename, mimetype, false);
    }

    public static Response createPostForTurtle(String filename) {
        return createPost(filename, TURTLE_MIMETYPE, true);
    }

    public static Response createPostForNQuads(String filename) {
        return createPost(filename, NQUAD_MIMETYPE);
    }

    public static Response createPostForTrig(String filename) {
        return createPost(filename, TRIG_MIMETYPE, true);
    }

    public static Response createPostForNTriples(String filename) {
        return createPost(filename, NTRIPLES_MIMETYPE, true);
    }

    public static Response createPostForN3(String filename) {
        return createPost(filename, N3_MIMETYPE, true);
    }

    public static Response createPostForRDFXML(String filename) {
        return createPost(filename, RDFXML_MIMETYPE, true);
    }

    /*

    $ curl --anyauth --user user:password -X POST -i \
    -d "state=clear" \
    -H "Content-type: application/x-www-form-urlencoded" \
    http://localhost:8002/manage/v2/forests/rest-example-1

    http://localhost:8002/manage/v2/databases/rest-example


    createPost
    "application/x-turtle"


        String input = new String(Files.readAllBytes(Paths.get(Configuration.RESOURCES+"turtle/history.ttl")));
        //System.out.println("Contents (Java 7) : " + contents);

        ClientResponse response = webResource.type("application/x-turtle")
                .post(ClientResponse.class, input);
        LOG.info("Status: "+ response.getStatus());
     */

}
