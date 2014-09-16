package vovkab.tagged.twitter.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utils {
    private Utils() {
    }

    public static void hideKeyboard(View view) {
        if (view == null || view.getContext() == null) return;

        InputMethodManager imm = getInputMethodManager(view.getContext());
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static InputMethodManager getInputMethodManager(Context context) {
        return getSystemService(context, Context.INPUT_METHOD_SERVICE);
    }

    public static <S> S getSystemService(Context context, String serviceName) {
        return (S) context.getSystemService(serviceName);
    }
}
