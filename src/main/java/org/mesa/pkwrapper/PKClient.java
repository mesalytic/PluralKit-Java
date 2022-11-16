package org.mesa.pkwrapper;

import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.mesa.pkwrapper.models.PKSystem;

import java.io.IOException;

import static org.mesa.pkwrapper.PKClientBuilder.httpClient;
import static org.mesa.pkwrapper.PKClientBuilder.token;

public class PKClient {
    /**
     * Returns the token linked with the {@link PKClientBuilder}
     * @return String
     */
    public String getToken() {
        return token;
    }

    /**
     * Returns the {@link PKSystem} object linked to the specified <b>systemRef</b>
     * @param systemRef Can be a {@link PKSystem} 5-character ID, a UUID, the ID of a Discord
     *                  account that is linked with the system, or the string `@me` to refer
     *                  to the currently authenticated system.
     * @return {@link PKSystem}
     * @throws IOException
     */
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
