package vovkab.tagged.twitter.utils;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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

    @Nullable
    public static String getString(EditText editText) {
        Editable editable = editText.getText();
        return editable != null ? String.valueOf(editable) : null;
    }
}
