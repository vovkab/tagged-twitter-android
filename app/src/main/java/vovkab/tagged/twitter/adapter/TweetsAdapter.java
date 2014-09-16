package vovkab.tagged.twitter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import vovkab.tagged.twitter.api.helper.ImageHelper;
import vovkab.tagged.twitter.api.model.Tweet;
import vovkab.tagged.twitter.api.model.User;
import vovkab.tagged.twitter.view.TweetView;

public class TweetsAdapter extends AdapterBase<Tweet, TweetView> {

    @Override public TweetView createView(LayoutInflater inflater, ViewGroup parent) {
        return new TweetView(inflater.getContext());
    }

    @Override public void bindView(int position, TweetView view, Tweet data) {
        view.setText(data.text);

        User user = data.user;
        view.setUserImage(ImageHelper.profileBigger(user.profileImageUrl));
        view.setUserName(user.name, user.screenName);
    }
}
