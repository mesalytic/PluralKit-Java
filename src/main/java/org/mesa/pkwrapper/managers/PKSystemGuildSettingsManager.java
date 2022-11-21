package org.mesa.pkwrapper.managers;

import okhttp3.RequestBody;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import org.mesa.pkwrapper.exceptions.EmptyManagerDataException;
import org.mesa.pkwrapper.models.PKSystemGuildSettings;
import org.mesa.pkwrapper.models.PKSystemSettings;
import org.mesa.pkwrapper.utils.APIRequest;
import org.mesa.pkwrapper.utils.Constants;

import java.io.IOException;

public class PKSystemGuildSettingsManager {
    protected final String id;
    protected final String guildID;
    JSONObject json;

    public PKSystemGuildSettingsManager(String id, String guildID) {
        this.id = id;
        this.guildID = guildID;
        this.json = new JSONObject();
    }

    public PKSystemGuildSettingsManager setProxyingEnabled(boolean state) {
        this.json.put("proxying_enabled", state);

        return this;
    }

    public PKSystemGuildSettingsManager setTag() {
        this.json.put("tag", JSONObject.NULL);

        return this;
    }

    public PKSystemGuildSettingsManager setTag(String tag) {
        this.json.put("tag", tag);

        return this;
    }

    public PKSystemGuildSettingsManager setTagEnabled(boolean state) {
        this.json.put("tag_enabled", state);

        return this;
    }

    public PKSystemGuildSettings update() throws EmptyManagerDataException, IOException {
        if (this.json.isEmpty()) throw new EmptyManagerDataException("Cannot call update() with no updated value");

        RequestBody body = RequestBody.create(this.json.toString(), Constants.JSON);

        JSONObject systemSettingsObject = APIRequest.patch(Constants.BASE_URL + "/systems/@me/guilds/" + guildID, body);

        return new PKSystemGuildSettings(systemSettingsObject, this.id, this.guildID);
    }
}
