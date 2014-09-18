package vovkab.tagged.twitter.view;

import android.content.Context;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import vovkab.tagged.twitter.R;
import vovkab.tagged.twitter.utils.ViewUtils;

public class TweetView extends FrameLayout {

    private TextView mTextView;
    private TextView mUserNameView;
    private ImageView mUserImageView;

    public TweetView(Context context) {
        this(context, null);
    }

    public TweetView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TweetView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.view_tweet, this);

        mTextView = (TextView) findViewById(R.id.tweet_text);
        mTextView.setAutoLinkMask(Linkify.WEB_URLS);
        mUserNameView = (TextView) findViewById(R.id.user_name);
        mUserImageView = (ImageView) findViewById(R.id.user_image);
    }

    public void setUserName(String userName, String screenName) {
        mUserNameView.setText(userName);
        if (!TextUtils.isEmpty(screenName)) {
            if (mUserNameView.length() > 0) {
                mUserNameView.append(" ");
            }
            mUserNameView.append("@");
            mUserNameView.append(screenName);
        }
        ViewUtils.hideIfEmpty(mUserNameView);
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

    public void setUserImage(String imageUrl) {
        Picasso.with(getContext()).load(imageUrl).into(mUserImageView);
    }
}
