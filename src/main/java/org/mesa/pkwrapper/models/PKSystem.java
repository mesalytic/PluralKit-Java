package org.mesa.pkwrapper.models;

import org.json.JSONObject;
import org.mesa.pkwrapper.utils.Utils;

public class PKSystem {
    protected final JSONObject json;
    protected final String id;
    protected final String uuid;

    /**
     * Used to instantiate a {@link PKSystem} object.
     * @param systemObject The JSON obtained via HTTP request
     */
    public PKSystem(JSONObject systemObject) {
        this.json = systemObject;
        this.id = this.json.getString("id");
        this.uuid = this.json.getString("uuid");
        //TODO: System Privacy
    }

    @Override
    public String toString() {
        return "PKSystem{" +
                "json=" + json +
                ", id='" + id + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    /**
     * Get the {@link PKSystem} object as a {@link JSONObject}
     * @return The {@link JSONObject} containing the {@link PKSystem} object
     */
    public JSONObject getAsJSON() {
        return json;
    }

    public String getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return Utils.coalesce(this.json.getString("name"), null);
    }

    public String getDescription() {
        return Utils.coalesce(this.json.getString("description"), null);
    }

    public String getTag() {
        return Utils.coalesce(this.json.getString("tag"), null);
    }

    public String getPronouns() {
        return Utils.coalesce(this.json.getString("pronouns"), null);
    }

    public String getAvatarURL() {
        return Utils.coalesce(this.json.getString("avatar_url"), null);
    }

    public String getBanner() {
        return Utils.coalesce(this.json.getString("banner"), null);
    }

    public String getColor() {
        return Utils.coalesce(this.json.getString("color"), null);
    }

    public String getCreated() {
        return Utils.coalesce(this.json.getString("created"), null);
    }
}
