package vovkab.tagged.twitter.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tweet {
    public final String id;
    public final String text;

    public Tweet(@JsonProperty("id") String id, @JsonProperty("text") String text) {
        this.id = id;
        this.text = text;
    }
}
