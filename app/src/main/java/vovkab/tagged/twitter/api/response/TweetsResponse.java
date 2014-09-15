package vovkab.tagged.twitter.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import vovkab.tagged.twitter.api.model.Tweet;

public class TweetsResponse {
    public final List<Tweet> tweets;

    public TweetsResponse(@JsonProperty("statuses") List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
