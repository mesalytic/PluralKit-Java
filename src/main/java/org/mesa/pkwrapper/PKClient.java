package org.mesa.pkwrapper;

import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.mesa.pkwrapper.models.PKSystem;
import org.mesa.pkwrapper.utils.APIRequest;
import org.mesa.pkwrapper.utils.Constants;

import java.io.IOException;

import static org.mesa.pkwrapper.PKClientBuilder.httpClient;
import static org.mesa.pkwrapper.PKClientBuilder.token;

public class PKClient {
    /**
     * Returns the token linked with the {@link PKClientBuilder}
     * @return String
     */
    public static String getToken() {
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
        JSONObject systemObject = APIRequest.get(Constants.BASE_URL + "/systems/" + systemRef);

        return new PKSystem(systemObject);
    }
}
