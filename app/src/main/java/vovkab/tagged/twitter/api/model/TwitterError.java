package vovkab.tagged.twitter.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class TwitterError {
    public final String message;
    public final int code;

    public TwitterError(@JsonProperty("message") String message, @JsonProperty("code") int code) {
        this.message = message;
        this.code = code;
    }
}
