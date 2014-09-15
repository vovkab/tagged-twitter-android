package vovkab.tagged.twitter.api.auth;

public class AuthManager implements Authorization {

    private Authorization mAuth;

    public AuthManager(Authorization auth) {
        mAuth = auth;
    }

    @Override public String getHeader() {
        return mAuth.getHeader();
    }

    @Override public boolean isEnabled() {
        return mAuth.isEnabled();
    }

    public void setAuthorization(Authorization auth) {
        mAuth = auth;
    }
}
