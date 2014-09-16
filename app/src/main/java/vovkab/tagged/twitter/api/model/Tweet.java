package vovkab.tagged.twitter.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tweet {
    public final String id;
    public final String text;
    public final User user;

    public Tweet(@JsonProperty("id") String id,
                 @JsonProperty("text") String text,
                 @JsonProperty("user") User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }
}
