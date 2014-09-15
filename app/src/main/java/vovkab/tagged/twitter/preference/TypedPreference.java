/*
 * Copyright (c) 2014 Bohemian Wrappsody AB.
 *
 * Author: Vladimir Baryshnikov <vovkab@gmail.com>
 * Date:   Mar 06, 2014
 */

package vovkab.tagged.twitter.preference;

import android.content.SharedPreferences;
import android.os.Build;

public class TypedPreference {

    protected final SharedPreferences mPreferences;
    protected final String mKey;

    public TypedPreference(SharedPreferences preferences, String key) {
        mPreferences = preferences;
        mKey = key;
    }

    public boolean isSet() {
        return mPreferences.contains(mKey);
    }

    public void delete() {
        commit(mPreferences.edit().remove(mKey));
    }

    protected void commit(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.FROYO) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

}
