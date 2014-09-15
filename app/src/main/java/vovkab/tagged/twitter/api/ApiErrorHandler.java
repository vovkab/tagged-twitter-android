package vovkab.tagged.twitter.api;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import vovkab.tagged.twitter.api.auth.AuthManager;
import vovkab.tagged.twitter.api.response.TwitterException;

public class ApiErrorHandler implements ErrorHandler {
    private final AuthManager mAuthManager;

    public ApiErrorHandler(AuthManager authManager) {
        mAuthManager = authManager;
    }

    @Override public Throwable handleError(RetrofitError cause) {
        TwitterException twitterException = (TwitterException) cause.getBodyAs(TwitterException.class);
        if (twitterException != null) {
            return twitterException;
        }
        return cause;
    }
}
