package vovkab.tagged.twitter.fragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

public class LoadingFragment extends Fragment {

    public static enum Mode {
        LOADING,
        CONTENT,
        NETWORK_ERROR,
    }

    public void showToast(String toast) {
        if (isAdded()) {
            Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
        }
    }
}
