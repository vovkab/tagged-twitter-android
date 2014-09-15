package vovkab.tagged.twitter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import vovkab.tagged.twitter.fragment.AuthFragment;

public class MainActivity extends ActionBarActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            showFragment(AuthFragment.class, null);
        }
    }

    public void showFragment(Class fragmentClass, Bundle args) {
        showFragment(fragmentClass.getName(), args);
    }

    public void showFragment(String fragmentName, Bundle args) {
        FragmentManager fm = getSupportFragmentManager();

        // Replace what ever we have with a new fragment
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = Fragment.instantiate(this, fragmentName, args);
        ft.replace(R.id.container, fragment);
        ft.commit();
    }

}
