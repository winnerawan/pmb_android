package net.winnerawan.androidx.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.winnerawan.androidx.di.ApplicationContext;
import net.winnerawan.androidx.di.PreferenceInfo;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String KEY_LOGGED_IN = "KEY_LOGGED_IN";
    private static final String KEY_FIRST_TIME = "KEY_FIRST_TIME";


    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public boolean isLoggedIn() {
        return mPrefs.getBoolean(KEY_LOGGED_IN, false);
    }

    @Override
    public void setLoggedIn(boolean loggedIn) {
        mPrefs.edit().putBoolean(KEY_LOGGED_IN, loggedIn).apply();
    }

    @Override
    public boolean isFirstTime() {
        return mPrefs.getBoolean(KEY_FIRST_TIME, true);
    }

    @Override
    public void setFirstTime(boolean isFirstTime) {
        mPrefs.edit().putBoolean(KEY_FIRST_TIME, isFirstTime).apply();
    }

}
