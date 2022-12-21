package org.mesa.pkwrapper.models;

import org.json.JSONObject;
import org.mesa.pkwrapper.managers.PKSystemAutoproxyManager;
import org.mesa.pkwrapper.utils.Utils;

public class PKSystemAPSettings {
    protected final JSONObject json;
    protected final String id;
    protected final String guildID;

    public PKSystemAPSettings(JSONObject json, String id, String guildID) {
        this.json = json;
        this.id = id;
        this.guildID = guildID;
    }

    public JSONObject getAsJSON() {
        return json;
    }

    public String getMode() {
        return this.json.getString("autoproxy_mode");
    }

    public String getMember() {
        return Utils.coalesce(this.json.getString("autoproxy_member"), null);
    }

    public String getLastLatch() {
        return Utils.coalesce(this.json.getString("last_latch_timestamp"), null);
    }

    public PKSystemAutoproxyManager getManager() {
        return new PKSystemAutoproxyManager(this.id, this.guildID);
    }
}
