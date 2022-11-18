package org.mesa.pkwrapper.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import org.mesa.pkwrapper.PKClient;
import org.mesa.pkwrapper.PKClientBuilder;
import org.mesa.pkwrapper.models.PKSystem;

import java.io.IOException;

public class APIRequest {
    private static final String token = PKClient.getToken();
    private static final OkHttpClient client = PKClientBuilder.httpClient;

    public static JSONObject get(String pathURL) throws IOException {
        Request request = new Request.Builder()
                .url(pathURL)
                .header("Authorization", token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return new JSONObject(response.body().string());
        }
    }

    public static JSONObject patch(String pathURL, RequestBody body) throws IOException {
        Request request = new Request.Builder()
                .url(pathURL)
                .patch(body)
                .header("Authorization", token)
                .build();

        Response response = client.newCall(request).execute();

        return new JSONObject(response.body().string());
    }
}
