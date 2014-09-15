package vovkab.tagged.twitter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
import vovkab.tagged.twitter.MainActivity;
import vovkab.tagged.twitter.R;
import vovkab.tagged.twitter.TaggedApp;
import vovkab.tagged.twitter.api.BearerToken;
import vovkab.tagged.twitter.api.TwitterClient;
import vovkab.tagged.twitter.api.auth.AuthManager;
import vovkab.tagged.twitter.api.auth.BasicAuthorization;
import vovkab.tagged.twitter.api.auth.BearerAuthorization;
import vovkab.tagged.twitter.api.response.TwitterException;
import vovkab.tagged.twitter.api.response.Oauth2TokenResponse;
import vovkab.tagged.twitter.preference.StringPreference;

public class AuthFragment extends LoadingFragment {

    @Inject TwitterClient mTwitter;
    @Inject @BearerToken StringPreference mBearerToken;
    @Inject AuthManager mAuthManager;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TaggedApp.get(getActivity()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (!mAuthManager.isEnabled()) {
            BasicAuthorization basicAuth = new BasicAuthorization(TwitterClient.CONSUMER_KEY, TwitterClient.CONSUMER_SECRET_KEY);
            TypedInput typedInput = new TypedByteArray("application/x-www-form-urlencoded;charset=UTF-8", "grant_type=client_credentials".getBytes());
            mTwitter.oauth2Token(basicAuth.getHeader(), typedInput, new Callback<Oauth2TokenResponse>() {
                @Override
                public void success(Oauth2TokenResponse oauth2TokenResponse, Response response) {
                    mBearerToken.set(oauth2TokenResponse.access_token);
                    mAuthManager.setAuthorization(new BearerAuthorization(mBearerToken.get()));
                    showSearchFragment();
                }

                @Override public void failure(RetrofitError error) {
                    if (error.getCause() instanceof TwitterException) {
                        TwitterException twitterException = (TwitterException) error.getCause();
                        showToast(twitterException.getTwitterErrorAsText());
                    }
                    error.printStackTrace();
                }
            });
        } else {
            showSearchFragment();
        }

    }

    private void showSearchFragment() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showFragment(SearchFragment.class, null);
        }
    }

}
