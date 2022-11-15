package org.mesa.pkwrapper;

import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.mesa.pkwrapper.models.PKSystem;

import java.io.IOException;

import static org.mesa.pkwrapper.PKClientBuilder.httpClient;
import static org.mesa.pkwrapper.PKClientBuilder.token;

public class PKClient {
    public String getToken() {
        return token;
    }

    public PKSystem getSystem(String systemRef) throws IOException {

        Request request = new Request.Builder()
                .url("https://api.pluralkit.me/v2/systems/" + systemRef)
                .header("Authorization", getToken())
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return new PKSystem(new JSONObject(response.body().string()));
        }
    }
}
