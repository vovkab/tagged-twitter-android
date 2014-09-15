package vovkab.tagged.twitter.api.auth;

import android.util.Base64;

import java.net.URLEncoder;

public class BasicAuthorization implements Authorization {

    private final String mHeader;

    public BasicAuthorization(String username, String password) {
        String credentials = URLEncoder.encode(username) + ":" + URLEncoder.encode(password);
        String base64 = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        mHeader = "Basic " + base64;
    }

    @Override public String getHeader() {
        return mHeader;
    }

    @Override public boolean isEnabled() {
        return true;
    }
}
