package org.mesa.pkwrapper.models;

import org.json.JSONObject;
import org.mesa.pkwrapper.managers.PKSystemGuildSettingsManager;
import org.mesa.pkwrapper.utils.Utils;

public class PKSystemGuildSettings {
    protected final JSONObject json;
    protected final String id;
    protected final String guildID;
    public PKSystemGuildSettings(JSONObject json, String id, String guildID) {
        this.json = json;
        this.id = id;
        this.guildID = guildID;
    }

    public String getGuildID() {
        return this.guildID;
    }

    public boolean isProxyingEnabled() {
        return this.json.getBoolean("proxying_enabled");
    }

    public String getTag() {
        return Utils.coalesce(this.json.getString("tag"), null);
    }

    public boolean isTagEnabled() {
        return this.json.getBoolean("tag_enabled");
    }

    public PKSystemGuildSettingsManager getManager() {
        return new PKSystemGuildSettingsManager(this.id, this.guildID);
    }
}
