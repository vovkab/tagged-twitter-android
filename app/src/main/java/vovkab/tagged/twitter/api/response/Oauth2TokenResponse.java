package vovkab.tagged.twitter.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Oauth2TokenResponse {
    public final String token_type;
    public final String access_token;

    public Oauth2TokenResponse(
        @JsonProperty("token_type") String tokenType,
        @JsonProperty("access_token") String accessToken) {

        this.token_type = tokenType;
        this.access_token = accessToken;
    }
}
