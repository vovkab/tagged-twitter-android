package vovkab.tagged.twitter.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    public final String name;
    public final String screenName;
    public final String profileImageUrl;

    public User(@JsonProperty("name") String name,
                @JsonProperty("screen_name") String screenName,
                @JsonProperty("profile_image_url") String profileImageUrl) {
        this.name = name;
        this.screenName = screenName;
        this.profileImageUrl = profileImageUrl;
    }
}
