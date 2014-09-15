package vovkab.tagged.twitter.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import vovkab.tagged.twitter.api.model.TwitterError;

public final class TwitterException extends Throwable {
    public final TwitterError[] errors;

    public TwitterException(@JsonProperty("errors") TwitterError[] errors) {
        this.errors = errors;
    }

    public String getTwitterErrorAsText() {
        StringBuilder sb = new StringBuilder();
        for (TwitterError error : errors) {
            sb.append("[Code: ").append(error.code).append("] ");
            sb.append(error.message).append("\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
