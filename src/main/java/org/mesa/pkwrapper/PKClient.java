package org.mesa.pkwrapper;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

import static org.mesa.pkwrapper.PKClientBuilder.httpClient;
import static org.mesa.pkwrapper.PKClientBuilder.token;

public class PKClient {
    public String getToken() {
        return token;
    }

    public String getSystem(String systemRef) throws IOException {

        Request request = new Request.Builder()
                .url("https://api.pluralkit.me/v2/systems/" + systemRef)
                .header("Authorization", getToken())
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
