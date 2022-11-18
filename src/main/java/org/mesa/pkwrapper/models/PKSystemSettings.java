package org.mesa.pkwrapper.models;

import org.json.JSONObject;
import org.mesa.pkwrapper.managers.PKSystemSettingsManager;
import org.mesa.pkwrapper.utils.Utils;

public class PKSystemSettings {
    protected final JSONObject json;
    protected final String id;

    public PKSystemSettings(JSONObject json, String id) {
        this.json = json;
        this.id = id;
    }

    public JSONObject getAsJSON() {
        return this.json;
    }

    /**
     * Get the system's time zone - shows timestamps in your local time
     * @return String with TimeZone Code
     */
    public String getTimezone() {
        return this.json.getString("timezone");
    }

    /**
     * Whether other users are able to mention the account linked with the system
     * @return Boolean
     */
    public boolean isPingsEnabled() {
        return this.json.getBoolean("pings_enabled");
    }

    /**
     * If this is set, latch-mode autoproxy will not keep autoproxying after this amount of time has elapsed since the last message sent in a server
     * @return the timeout in seconds
     */
    public int getLatchTimeout() {
        return Utils.coalesce(this.json.getInt("latch_timeout"), null);
    }

    /**
     * Whether members created through the bot have privacy settings set to private by default
     * <br>
     * <b>Members created through the API are not affected by this.</b>
     * <br>
     * <b>The privacy keys for members created via the API must be sent in the JSON creation payload.</b>
     * @return boolean
     */
    public boolean isMemberDefaultPrivate() {
        return this.json.getBoolean("member_default_private");
    }

    /**
     * Whether groups created through the bot have privacy settings set to private by default
     * <br>
     * <b>Groups created through the API are not affected by this.</b>
     * <br>
     * <b>The privacy keys for groups created via the API must be sent in the JSON creation payload.</b>
     * @return boolean
     */
    public boolean isGroupDefaultPrivate() {
        return this.json.getBoolean("group_default_private");
    }

    /**
     * Whether the bot shows the system's own private information without a <code>--private</code> flag.
     * @return boolean
     */
    public boolean showPrivateInfo() {
        return this.json.getBoolean("show_private_info");
    }

    /**
     * The maximum number of registered members for a system.
     * @return The member limit, defaults to 1000
     */
    public int getMemberLimit() {
        return this.json.getInt("member_limit");
    }

    /**
     * The maximum number of registered groups for a system.
     * @return The group limit, defaults to 250
     */
    public int getGroupLimit() {
        return this.json.getInt("group_limit");
    }

    public PKSystemSettingsManager getManager() {
        return new PKSystemSettingsManager(id);
    }
}
