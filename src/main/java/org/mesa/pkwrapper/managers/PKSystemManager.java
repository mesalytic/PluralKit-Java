package org.mesa.pkwrapper.managers;

import okhttp3.*;
import org.json.JSONObject;
import org.mesa.pkwrapper.PKClient;
import org.mesa.pkwrapper.PKClientBuilder;
import org.mesa.pkwrapper.exceptions.EmptyManagerDataException;
import org.mesa.pkwrapper.exceptions.InvalidHexColorException;
import org.mesa.pkwrapper.exceptions.InvalidImageURLException;
import org.mesa.pkwrapper.exceptions.StringTooLongException;
import org.mesa.pkwrapper.models.PKSystem;
import org.mesa.pkwrapper.utils.Constants;
import org.mesa.pkwrapper.utils.Utils;

import java.io.IOException;

public class PKSystemManager {
    protected final String systemRef;
    JSONObject json;

    public PKSystemManager(String systemRef) {
        this.systemRef = systemRef;
        this.json = new JSONObject();
    }

    /**
     * Sets the name in the {@link PKSystemManager} instance.
     * @param name New name of the {@link PKSystem}
     * @return The same instance of {@link PKSystemManager} with the saved value.
     * @throws StringTooLongException If the name is over the 100 characters limit
     */
    public PKSystemManager setName(String name) throws StringTooLongException {
        if (name.length() > 100) throw new StringTooLongException("String name is over the 100 character limit");

        this.json.put("name", name);

        return this;
    }

    /**
     * Sets the description in the {@link PKSystemManager} instance.
     * @param description New description of the {@link PKSystem}
     * @return The same instance of {@link PKSystemManager} with the saved value.
     * @throws StringTooLongException If the description is over the 1000 characters limit
     */
    public PKSystemManager setDescription(String description) throws StringTooLongException {
        if (description.length() > 1000) throw new StringTooLongException("String description is over the 1000 character limit");

        this.json.put("description", description);

        return this;
    }

    /**
     * Sets the tag in the {@link PKSystemManager} instance.
     * @param tag New tag of the {@link PKSystem}
     * @return The same instance of {@link PKSystemManager} with the saved value.
     */
    public PKSystemManager setTag(String tag) {
        this.json.put("tag", tag);

        return this;
    }

    /**
     * Sets the pronouns in the {@link PKSystemManager} instance.
     * @param pronouns New pronouns of the {@link PKSystem}
     * @return The same instance of {@link PKSystemManager} with the saved value.
     * @throws StringTooLongException If the pronouns are over the 100 characters limit
     */
    public PKSystemManager setPronouns(String pronouns) throws StringTooLongException {
        if (pronouns.length() > 100) throw new StringTooLongException("String pronouns is over the 100 character limit");

        this.json.put("pronouns", pronouns);

        return this;
    }

    /**
     * Sets the Avatar URL in the {@link PKSystemManager} instance.
     * @param avatarURL The new avatar URL of the {@link PKSystem}
     * @return The same instance of {@link PKSystemManager} with the saved value.
     * @throws InvalidImageURLException If the URL is not publicly accessible, or if it's not an image URL
     * @throws StringTooLongException If the URL is over the 256 characters limit
     */
    public PKSystemManager setAvatarURL(String avatarURL) throws InvalidImageURLException, StringTooLongException {
        if (!Utils.isValidImageURL(avatarURL)) throw new InvalidImageURLException("The specified URL is not a valid avatar URL");
        if (avatarURL.length() > 256) throw new StringTooLongException("String avatarURL is over the 256 character limit");

        this.json.put("avatar_url", avatarURL);

        return this;
    }

    /**
     * Sets the banner in the {@link PKSystemManager} instance.
     * @param bannerURL The new banner of the {@link PKSystem}
     * @return The same instance of {@link PKSystemManager} with the saved value.
     * @throws InvalidImageURLException If the URL is not publicly accessible, or if it's not an image URL
     * @throws StringTooLongException If the URL is over the 256 characters limit
     */
    public PKSystemManager setBanner(String bannerURL) throws InvalidImageURLException, StringTooLongException {
        if (!Utils.isValidImageURL(bannerURL)) throw new InvalidImageURLException("The specified URL is not a valid banner URL");
        if (bannerURL.length() > 256) throw new StringTooLongException("String bannerURL is over the 256 character limit");

        this.json.put("banner", bannerURL);

        return this;
    }

    /**
     * Sets the color in the {@link PKSystemManager} instance.
     * @param hexCode The new color of the {@link PKSystem}
     * @return The same instance of {@link PKSystemManager} with the saved value.
     * @throws StringTooLongException If the hex-code is over the 6 characters limit
     * @throws InvalidHexColorException If the hex-code starts with an <b>#</b> or if the hex code is not within <b>000000</b> - <b>FFFFFF</b>
     */
    public PKSystemManager setColor(String hexCode) throws StringTooLongException, InvalidHexColorException {
        if (hexCode.startsWith("#")) throw new InvalidHexColorException("String hexCode must not start with an #");

        if (hexCode.length() > 6) throw new StringTooLongException("String hexCode is over the 6 character limit");
        if (!Utils.isValidHexColor(hexCode)) throw new InvalidHexColorException("String hexCode is not a valid hexadecimal color code.");

        this.json.put("color", hexCode);

        return this;
    }

    /**
     * Sends all the updated value to the API.
     * @return A new {@link PKSystem} with the new updated values.
     * @throws IOException
     * @throws EmptyManagerDataException If the data sent to the API is empty
     */
    public PKSystem update() throws IOException, EmptyManagerDataException {
        if (this.json.isEmpty()) throw new EmptyManagerDataException("Cannot call update() with no updated value");

        OkHttpClient client = PKClientBuilder.httpClient;
        RequestBody body = RequestBody.create(this.json.toString(), Constants.JSON);

        Request request = new Request.Builder()
                .url(Constants.BASE_URL + "/systems/" + systemRef)
                .patch(body)
                .header("Authorization", PKClient.getToken())
                .build();

        Response response = client.newCall(request).execute();

        return new PKSystem(new JSONObject(response.body().string()));
    }
}
