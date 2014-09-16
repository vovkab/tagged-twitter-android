package vovkab.tagged.twitter.api.helper;

public class ImageHelper {
    private ImageHelper() {
    }

    public static String profileBigger(String normal) {
        return getImage(normal, "bigger");
    }

    public static String getImage(String normal, String newSize) {
        if (normal != null) {
            return normal.replace("normal", newSize);
        } else {
            return null;
        }
    }
}
