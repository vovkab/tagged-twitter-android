/*
 * Copyright (c) 2014 Bohemian Wrappsody AB.
 *
 * Author: Vladimir Baryshnikov <vovkab@gmail.com>
 * Date:   Mar 06, 2014
 */

package vovkab.tagged.twitter.preference;

import android.content.Context;
import android.content.SharedPreferences;

import rx.Observable;
import rx.subjects.BehaviorSubject;

public class StringPreference extends TypedPreference {

    public static final String DEFAULT_VALUE = "";
    private final String mDefaultValue;
    private BehaviorSubject<String> mSubject;

    public StringPreference(SharedPreferences preferences, String key) {
        this(preferences, key, DEFAULT_VALUE);
    }

    public StringPreference(SharedPreferences preferences, String key, String defValue) {
        super(preferences, key);
        mDefaultValue = defValue;
    }

    public StringPreference(Context context, SharedPreferences preferences, int keyResId) {
        this(preferences, context.getString(keyResId));
    }

    public StringPreference(Context context, SharedPreferences preferences, int keyResId, int defValueResId) {
        this(preferences, context.getString(keyResId), context.getResources().getString(defValueResId));
    }

    public String get() {
        return mPreferences.getString(mKey, mDefaultValue);
    }

    public void set(String value) {
        commit(mPreferences.edit().putString(mKey, value));
        notifySubscribers(value);
    }

    @Override
    public void delete() {
        super.delete();
        notifySubscribers(null);
    }

    public Observable<String> getObservable() {
        if (mSubject == null) {
            mSubject = BehaviorSubject.create(get());
        }
        return mSubject.asObservable();
    }

    private void notifySubscribers(String value) {
        if (mSubject == null) return;
        mSubject.onNext(value);
    }

}
