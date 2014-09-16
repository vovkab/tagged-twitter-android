package vovkab.tagged.twitter.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class ViewUtils {
    private ViewUtils() {
    }

    public static void showView(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public static void hideIfEmpty(TextView textView) {
        showView(textView, !TextUtils.isEmpty(textView.getText()));
    }
}
