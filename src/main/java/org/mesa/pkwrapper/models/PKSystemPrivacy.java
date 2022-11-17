package org.mesa.pkwrapper.models;

import org.json.JSONObject;
import org.mesa.pkwrapper.managers.PKSystemPrivacyManager;

public class PKSystemPrivacy {
    protected final JSONObject json;
    protected final String id;

    public PKSystemPrivacy(JSONObject systemPrivacyObject, String id) {
        this.id = id;
        this.json = systemPrivacyObject;
    }

    public JSONObject getAsJSON() {
        return this.json;
    }

    public String getGroupListPrivacy() {
        return this.json.getString("group_list_privacy");
    }

    public String getMemberListPrivacy() {
        return this.json.getString("member_list_privacy");
    }

    public String getDescriptionPrivacy() {
        return this.json.getString("description_privacy");
    }

    public String getPronounPrivacy() {
        return this.json.getString("pronoun_privacy");
    }

    public String getFrontPrivacy() {
        return this.json.getString("front_privacy");
    }

    public String getFrontHistoryPrivacy() {
        return this.json.getString("front_history_privacy");
    }

    public PKSystemPrivacyManager getPrivacyManager() {
        return new PKSystemPrivacyManager(id);
    }
}
