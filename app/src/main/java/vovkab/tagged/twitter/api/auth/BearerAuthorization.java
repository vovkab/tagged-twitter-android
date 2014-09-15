package vovkab.tagged.twitter.api.auth;

public class BearerAuthorization implements Authorization {
    private final String mBearerHeader;

    public BearerAuthorization(String accessToken) {
        mBearerHeader = "Bearer " + accessToken;
    }

    @Override public String getHeader() {
        return mBearerHeader;
    }

    @Override public boolean isEnabled() {
        return true;
    }
}
