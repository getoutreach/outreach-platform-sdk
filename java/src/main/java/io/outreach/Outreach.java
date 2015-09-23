package io.outreach;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import io.outreach.exception.OutreachSecurityException;
import io.outreach.security.TrustedHostnameVerifier;
import io.outreach.security.TrustedSSLSocketFactory;

/**
 * Example project for Outreach platform consumers.
 */
public class Outreach {

    /**
     * Authorization access credential, this is returned from redirected
     * authorization requests from api.outreach.io/oauth/authorize.
     */
    private final String authorizationCode;

    /**
     * Credentials representing the application, namely the application
     * identifier, secret, and other metadata
     */
    private final ApplicationCredentials applicationCredentials;

    /**
     * Request access bearer-credential, this short-lived credential is returned
     * from api.outreach.io/oauth/token.
     */
    private String requestBearer = null;

    /**
     * Refresh token used to generate a new bearer token after the original
     * authorization code has been consumed.
     */
    private String refreshBearer = null;

    private final KeyStore trustStore;

    public Outreach(final ApplicationCredentials applicationCredentials, final String authorizationCode) {
        this(applicationCredentials, authorizationCode, null);
    }

    public Outreach(final ApplicationCredentials applicationCredentials,
                    final String authorizationCode,
                    final KeyStore trustStore) {
        this.applicationCredentials = applicationCredentials;
        this.authorizationCode = authorizationCode;
        this.trustStore = trustStore;
    }

    /**
     * Allows adding a single prospect for the associated account to the local
     * bearer credential.
     *
     * @param prospect
     *            the JSONObject API-formatted request containing the prospect
     *            to be created, this should ideally be wrapped in a domain
     *            object.
     * @return a JSONObject blob of the response, containing the created
     *         prospect identifier and creation/update timestamps.
     */
    public JSONObject addProspect(final String prospect) {
        try {
            // Refresh access token on each request, the first request will use the authorization
            // code and subsequent requests will use the refresh token from the initial exchange.
            this.fetchAccessToken();

            final HttpsURLConnection connection = connectTo("https://api.outreach.io/1.0/prospect");

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", new String("Bearer " + this.requestBearer));
            connection.setRequestProperty("Content-Type", "application/json");

            try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
                writer.write(prospect);
            }

            final JSONObject response;
            try (BufferedReader readStream = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = (JSONObject) JSONValue.parse(readStream);
            }

            return response;
        } catch (Throwable throwable) {
            throw new OutreachSecurityException(throwable);
        }
    }

    private HttpsURLConnection connectTo(String urlString) throws MalformedURLException,
                                                                  IOException,
                                                                  KeyManagementException,
                                                                  NoSuchAlgorithmException {
        return connectTo(new URL(urlString));
    }

    private HttpsURLConnection connectTo(URL url) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        if (trustStore != null) {
            connection.setSSLSocketFactory(TrustedSSLSocketFactory.get(trustStore));
            connection.setHostnameVerifier(new TrustedHostnameVerifier(trustStore));
        }

        return connection;
    }

    /**
     * Creates a new bearer token associated with this instance, this must be
     * done before any calls to the API can be made. This is automatically
     * called on all API requests, which is somewhat overkill since the access
     * token should be valid for a period of time (TTL is specified in the
     * response payload below). <br />
     * <br />
     * <b>NOTE</b>: Authorization codes will only grant a single bearer token
     * which expires after timeout or use; to generate a new one either get a
     * new authorize code or use the refresh token in the response (change:
     * &code and &grant_type).
     */
    private void fetchAccessToken() {
        try {
            final HttpsURLConnection connection = connectTo("https://api.outreach.io/oauth/token");

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            // Use a refresh token if one was previously provided.
            final String token;
            if (this.refreshBearer != null) {
                token = "&grant_type=refresh_token&refresh_token=" + this.refreshBearer;
            } else {
                token = "&grant_type=authorization_code&code=" + this.authorizationCode;
            }

            try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
                writer.write("client_id=" + this.applicationCredentials.APP_IDENTIFIER + "&client_secret="
                           + this.applicationCredentials.APP_SECRET_KEY + "&redirect_uri="
                           + this.applicationCredentials.APP_RETURN_URI + token);
            }

            if (connection.getResponseCode() == 401) {
                System.out.println("Server returned unauthorized response, verify that the authorize_code hasn't already been used.");
            }

            final JSONObject response;
            try (BufferedReader readStream = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = (JSONObject) JSONValue.parse(readStream);
            }

            this.requestBearer = response.get("access_token").toString();
            this.refreshBearer = response.get("refresh_token").toString();
        } catch (Throwable throwable) {
            throw new OutreachSecurityException(throwable);
        }

    }

    public static class ApplicationCredentials {
        /**
         * Application credentials, these are generated when a client
         * application is provisioned in api.outreach.io/oauth/applications.
         */
        protected final String APP_IDENTIFIER, APP_SECRET_KEY, APP_RETURN_URI;

        public ApplicationCredentials(final String app_identifier, final String app_secret_key,
                                      final String app_return_uri) {
            this.APP_IDENTIFIER = app_identifier;
            this.APP_SECRET_KEY = app_secret_key;
            this.APP_RETURN_URI = app_return_uri;
        }
    }
}
