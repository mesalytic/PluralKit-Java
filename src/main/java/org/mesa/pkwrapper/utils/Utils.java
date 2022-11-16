package org.mesa.pkwrapper.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    /**
     * Nullish coalescent operator equivalent [ ?? ]
     * If <b>object</b> is null, it will return <b>defaultObject</b>
     * @param object
     * @param defaultObject
     * @return
     * @param <T>
     */
    public static <T> T coalesce(T object, T defaultObject) {
        return object != null ? object : defaultObject;
    }

    public static boolean isValidImageURL(String url) {
        try {
            BufferedImage image = ImageIO.read(new URL(url));

            if (image != null) {
                return true;
            } else return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();

            return false;
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }
    }

    public static boolean isValidHexColor(String hexColor) {
        Pattern pattern = Pattern.compile("^([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
        Matcher matcher = pattern.matcher(hexColor);

        return matcher.matches();
    }
}
