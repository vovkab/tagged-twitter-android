package vovkab.tagged.twitter.api.auth;

public interface Authorization {
    public String getHeader();

    public boolean isEnabled();
}
