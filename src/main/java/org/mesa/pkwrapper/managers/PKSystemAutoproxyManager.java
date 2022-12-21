package org.mesa.pkwrapper.managers;

import okhttp3.RequestBody;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import org.mesa.pkwrapper.enums.AutoproxyMode;
import org.mesa.pkwrapper.enums.SystemPrivacyState;
import org.mesa.pkwrapper.exceptions.EmptyManagerDataException;
import org.mesa.pkwrapper.models.PKSystemAPSettings;
import org.mesa.pkwrapper.models.PKSystemGuildSettings;
import org.mesa.pkwrapper.utils.APIRequest;
import org.mesa.pkwrapper.utils.Constants;

import java.io.IOException;

public class PKSystemAutoproxyManager {
    protected final String id;
    protected final String guildID;
    JSONObject json;

    public PKSystemAutoproxyManager(String id, String guildID) {
        this.id = id;
        this.guildID = guildID;
        this.json = new JSONObject();
    }

    public PKSystemAutoproxyManager setMode(AutoproxyMode mode) {
        this.json.put("autoproxy_mode", mode.getState());

        return this;
    }

    /**
     *
     * @param memberID Must be <code>null</code> if the Autoproxy Mode is set to AutoproxyMode.FRONT
     * @return
     */
    public PKSystemAutoproxyManager setMember(@Nullable String memberID) {
        if (memberID == null) this.json.put("autoproxy_member", JSONObject.NULL);
        else this.json.put("autoproxy_member", memberID);

        return this;
    }

    public PKSystemAPSettings update() throws EmptyManagerDataException, IOException {
        if (this.json.isEmpty()) throw new EmptyManagerDataException("Cannot call update() with no updated value");

        RequestBody body = RequestBody.create(this.json.toString(), Constants.JSON);

        JSONObject systemSettingsObject = APIRequest.patch(Constants.BASE_URL + "/systems/@me/autoproxy?guild_id=" + guildID, body);

        return new PKSystemAPSettings(systemSettingsObject, this.id, this.guildID);
    }
}
