package vovkab.tagged.twitter.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import vovkab.tagged.twitter.R;

public class TweetView extends FrameLayout {

    private TextView mTextView;

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

        mTextView = (TextView) findViewById(R.id.text);
    }

    public void setText(String text) {
        mTextView.setText(text);
    }
}
