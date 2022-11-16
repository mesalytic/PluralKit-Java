package org.mesa.pkwrapper.models;

import org.json.JSONObject;

public class PKSystemPrivacy {
    protected final JSONObject json;

    public PKSystemPrivacy(JSONObject systemPrivacyObject) {
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
}
