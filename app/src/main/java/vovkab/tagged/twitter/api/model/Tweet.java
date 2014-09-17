package vovkab.tagged.twitter.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tweet implements Parcelable {
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

    public static Creator<Tweet> CREATOR = new Creator<Tweet>() {
        @Override public Tweet createFromParcel(Parcel source) {
            String id = source.readString();
            String text = source.readString();
            User user = source.readParcelable(User.class.getClassLoader());
            return new Tweet(id, text, user);
        }

        @Override public Tweet[] newArray(int size) {
            return new Tweet[size];
        }
    };

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(text);
        dest.writeParcelable(user, 0);
    }

    @Override public int describeContents() {
        return 0;
    }
}
