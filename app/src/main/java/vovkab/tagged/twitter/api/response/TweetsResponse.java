package vovkab.tagged.twitter.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import vovkab.tagged.twitter.api.model.Tweet;

public class TweetsResponse {
    public final ArrayList<Tweet> tweets;

    public TweetsResponse(@JsonProperty("statuses") ArrayList<Tweet> tweets) {
        this.tweets = tweets;
    }
}
