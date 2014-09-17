package vovkab.tagged.twitter.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import vovkab.tagged.twitter.api.helper.ImageHelper;

public class User implements Parcelable {
    public final String name;
    public final String screenName;
    public final String profileImageUrl;
    public final String profileImageBiggerUrl;

    public User(@JsonProperty("name") String name,
                @JsonProperty("screen_name") String screenName,
                @JsonProperty("profile_image_url") String profileImageUrl) {
        this.name = name;
        this.screenName = screenName;
        this.profileImageUrl = profileImageUrl;
        this.profileImageBiggerUrl = ImageHelper.profileBigger(profileImageUrl);
    }

    public static Creator<User> CREATOR = new Creator<User>() {
        @Override public User createFromParcel(Parcel source) {
            String name = source.readString();
            String screenName = source.readString();
            String profileImageUrl = source.readString();
            return new User(name, screenName, profileImageUrl);
        }

        @Override public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(screenName);
        dest.writeString(profileImageUrl);
    }

    @Override public int describeContents() {
        return 0;
    }
}
