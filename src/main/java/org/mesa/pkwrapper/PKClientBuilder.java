package org.mesa.pkwrapper;

import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import org.mesa.pkwrapper.interceptors.RateLimitInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * Used to create <b>new</b> {@link PKClient} instances.
 */
public record PKClientBuilder() {
    private static final PKClient client = new PKClient();

    static String token;
    static OkHttpClient httpClient;

    static final Dispatcher dispatcher = new Dispatcher();

    public PKClientBuilder create(String token) {
        PKClientBuilder.token = token;

        return this;
    }

    public PKClient build() {
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
