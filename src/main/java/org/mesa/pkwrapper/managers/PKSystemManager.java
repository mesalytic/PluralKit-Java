package org.mesa.pkwrapper.managers;

import okhttp3.*;
import org.json.JSONObject;
import org.mesa.pkwrapper.PKClient;
import org.mesa.pkwrapper.PKClientBuilder;
import org.mesa.pkwrapper.exceptions.InvalidHexColorException;
import org.mesa.pkwrapper.exceptions.InvalidImageURLException;
import org.mesa.pkwrapper.exceptions.StringTooLongException;
import org.mesa.pkwrapper.utils.Utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PKSystemManager {
    protected final String systemRef;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    JSONObject json;

    public PKSystemManager(String systemRef) {
        this.systemRef = systemRef;
        this.json = new JSONObject();
    }

    public PKSystemManager setName(String name) throws StringTooLongException {
        if (name.length() > 100) throw new StringTooLongException("String name is over the 100 character limit");

        this.json.put("name", name);

        return this;
    }

    public PKSystemManager setDescription(String description) throws StringTooLongException {
        if (description.length() > 1000) throw new StringTooLongException("String description is over the 1000 character limit");

        this.json.put("description", description);

        return this;
    }

    public PKSystemManager setTag(String tag) {
        this.json.put("tag", tag);

        return this;
    }

    public PKSystemManager setPronouns(String pronouns) throws StringTooLongException {
        if (pronouns.length() > 100) throw new StringTooLongException("String pronouns is over the 100 character limit");

        this.json.put("pronouns", pronouns);

        return this;
    }

    public PKSystemManager setAvatarURL(String avatarURL) throws InvalidImageURLException, StringTooLongException {
        if (!Utils.isValidImageURL(avatarURL)) throw new InvalidImageURLException("The specified URL is not a valid avatar URL");
        if (avatarURL.length() > 256) throw new StringTooLongException("String avatarURL is over the 256 character limit");

        this.json.put("avatar_url", avatarURL);

        return this;
    }

    public PKSystemManager setBanner(String bannerURL) throws InvalidImageURLException, StringTooLongException {
        if (!Utils.isValidImageURL(bannerURL)) throw new InvalidImageURLException("The specified URL is not a valid banner URL");
        if (bannerURL.length() > 256) throw new StringTooLongException("String bannerURL is over the 256 character limit");

        this.json.put("banner", bannerURL);

        return this;
    }

    public PKSystemManager setColor(String hexCode) throws StringTooLongException, InvalidHexColorException {
        if (hexCode.startsWith("#")) hexCode = hexCode.substring(1);

        if (hexCode.length() > 6) throw new StringTooLongException("String hexCode is over the 6 character limit");
        if (!Utils.isValidHexColor(hexCode)) throw new InvalidHexColorException("String hexCode is not a valid hexadecimal color code.");

        this.json.put("color", hexCode);

        return this;
    }

    public String update() throws IOException {
        OkHttpClient client = PKClientBuilder.httpClient;
        RequestBody body = RequestBody.create(this.json.toString(), JSON);

        Request request = new Request.Builder()
                .url("https://api.pluralkit.me/v2/systems/" + systemRef)
                .patch(body)
                .header("Authorization", PKClient.getToken())
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }
}
