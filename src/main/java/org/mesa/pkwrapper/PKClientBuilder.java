package org.mesa.pkwrapper;

import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import org.mesa.pkwrapper.exceptions.InvalidTokenException;
import org.mesa.pkwrapper.interceptors.RateLimitInterceptor;
import org.mesa.pkwrapper.utils.Checks;

import java.util.concurrent.TimeUnit;

/**
 * Used to create <b>new</b> {@link PKClient} instances.
 */
public record PKClientBuilder() {
    private static final PKClient client = new PKClient();

    static String token;
    public static OkHttpClient httpClient;

    static final Dispatcher dispatcher = new Dispatcher();

    /**
     * Used to add the token to the builder.
     * @param token The token that you obtained via PluralKit.
     * @return The Builder with the token
     */
    public PKClientBuilder create(String token) {
        PKClientBuilder.token = token;

        return this;
    }

    /**
     * Builds the {@link PKClient} with the specified token specified.
     * @return A {@link PKClient} instance.
     */
    public PKClient build() throws InvalidTokenException {
        Checks.token(token);
        dispatcher.setMaxRequestsPerHost(25);

        final ConnectionPool connectionPool = new ConnectionPool(5, 5, TimeUnit.SECONDS);

        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .connectionPool(connectionPool)
                .retryOnConnectionFailure(true)
                .addInterceptor(new RateLimitInterceptor());

        httpClient = builder.build();

        return client;
    }
}
