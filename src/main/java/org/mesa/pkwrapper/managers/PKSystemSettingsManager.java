package org.mesa.pkwrapper.managers;

import okhttp3.RequestBody;
import org.json.JSONObject;
import org.mesa.pkwrapper.exceptions.EmptyManagerDataException;
import org.mesa.pkwrapper.exceptions.InvalidTimeZoneException;
import org.mesa.pkwrapper.models.PKSystemSettings;
import org.mesa.pkwrapper.utils.APIRequest;
import org.mesa.pkwrapper.utils.Constants;
import org.mesa.pkwrapper.utils.Utils;

import java.io.IOException;

public class PKSystemSettingsManager {
    protected final String id;
    JSONObject json;

    public PKSystemSettingsManager(String id) {
        this.id = id;
        this.json = new JSONObject();
    }

    public PKSystemSettingsManager setTimezone(String timezone) throws InvalidTimeZoneException {
        if (!Utils.validTimeZone(timezone)) throw new InvalidTimeZoneException(timezone + " is not a valid TimeZone ID");

        this.json.put("timezone", timezone);

        return this;
    }

    public PKSystemSettingsManager setPingsEnabled(boolean state) {
        this.json.put("pings_enabled", state);

        return this;
    }

    public PKSystemSettingsManager setLatchTimeout(int timeout) {
        this.json.put("latch_timeout", timeout);

        return this;
    }

    public PKSystemSettingsManager setMemberDefaultPrivate(boolean state) {
        this.json.put("member_default_private", state);

        return this;
    }

    public PKSystemSettingsManager setGroupDefaultPrivate(boolean state) {
        this.json.put("group_default_private", state);

        return this;
    }

    public PKSystemSettingsManager setShowPrivateInfo(boolean state) {
        this.json.put("show_private_info", state);

        return this;
    }

    public PKSystemSettings update() throws EmptyManagerDataException, IOException {
        if (this.json.isEmpty()) throw new EmptyManagerDataException("Cannot call update() with no updated value");

        RequestBody body = RequestBody.create(this.json.toString(), Constants.JSON);

        JSONObject systemSettingsObject = APIRequest.patch(Constants.BASE_URL + "/systems/" + id + "/settings", body);

        return new PKSystemSettings(systemSettingsObject, id);
    }
}
