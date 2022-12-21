package org.mesa.pkwrapper.models;

import org.json.JSONObject;
import org.mesa.pkwrapper.PKClient;
import org.mesa.pkwrapper.exceptions.NoGuildSettingsException;
import org.mesa.pkwrapper.exceptions.NotAuthorizedException;
import org.mesa.pkwrapper.managers.PKSystemManager;
import org.mesa.pkwrapper.utils.APIRequest;
import org.mesa.pkwrapper.utils.Constants;
import org.mesa.pkwrapper.utils.Utils;

import java.io.IOException;

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
    }

    @Override
    public String toString() {
        return "PKSystem{" +
                "id='" + id + '\'' +
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

    public PKSystemPrivacy getPrivacy() {
        // needs auth ?
        return new PKSystemPrivacy(Utils.coalesce(this.json.getJSONObject("privacy"), null), getId());
    }

    public PKSystemManager getManager() throws NotAuthorizedException {
        if (PKClient.getToken() == null) throw new NotAuthorizedException("You must specify a token in the PKClientBuilder instance.");

        return new PKSystemManager(getId());
    }

    public PKSystemSettings getSettings() throws IOException, NotAuthorizedException {
        if (PKClient.getToken() == null) throw new NotAuthorizedException("You must specify a token in the PKClientBuilder instance.");

        JSONObject systemSettingsObject = APIRequest.get(Constants.BASE_URL + "/systems/" + getId() + "/settings");

        return new PKSystemSettings(systemSettingsObject, getId());
    }

    /**
     * <b>You must have already updated per-guild settings for your system in the target Guild before being able to get them via the API.</b>
     * @param guildID The ID of the guild you want to see the settings from.
     * @return {@link PKSystemGuildSettings}
     * @throws IOException Thrown by the Http Client if any errors occurred.
     * @throws NoGuildSettingsException Thrown if no Guild Settings have been specified using the Discord bot.
     * @throws NotAuthorizedException Thrown if there is no token included in the {@link org.mesa.pkwrapper.PKClientBuilder}.
     */
    public PKSystemGuildSettings getGuildSettings(String guildID) throws IOException, NoGuildSettingsException, NotAuthorizedException {
        if (PKClient.getToken() == null) throw new NotAuthorizedException("You must specify a token in the PKClientBuilder instance.");

        JSONObject systemGuildSettingsObject = APIRequest.get(Constants.BASE_URL + "/systems/@me/guilds/" + guildID);

        if (systemGuildSettingsObject.has("message")) throw new NoGuildSettingsException("You must first update per-guild settings for the System in the server.");

        return new PKSystemGuildSettings(systemGuildSettingsObject, getId(), guildID);
    }

    public PKSystemAPSettings getAutoproxySettings(String guildID) throws NotAuthorizedException, IOException {
        if (PKClient.getToken() == null) throw new NotAuthorizedException("You must specify a token in the PKClientBuilder instance.");

        JSONObject systemAutoproxyObject = APIRequest.get(Constants.BASE_URL + "/systems/@me/autoproxy?guild_id=" + guildID);

        return new PKSystemAPSettings(systemAutoproxyObject, getId(), guildID);
    }
}
