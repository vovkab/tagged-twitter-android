package vovkab.tagged.twitter.api.auth;

public class EmptyAuthorization implements Authorization {
    private static final EmptyAuthorization SINGLETON = new EmptyAuthorization();

    public static EmptyAuthorization getInstance() {
        return SINGLETON;
    }

    private EmptyAuthorization() {
    }

    @Override public String getHeader() {
        return "";
    }

    @Override public boolean isEnabled() {
        return false;
    }
}
