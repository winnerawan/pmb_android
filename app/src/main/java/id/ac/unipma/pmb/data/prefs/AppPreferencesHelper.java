package id.ac.unipma.pmb.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.ac.unipma.pmb.di.ApplicationContext;
import id.ac.unipma.pmb.di.PreferenceInfo;

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
    private static final String KEY_STEP = "KEY_STEP";
    private static final String KEY_STUDENT = "KEY_STUDENT";

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

    @Override
    public int getStep() {
        return mPrefs.getInt(KEY_STEP, 1);
    }

    @Override
    public void setStep(int step) {
        mPrefs.edit().putInt(KEY_STEP, step).apply();
    }

    @Override
    public String getStudentName() {
        return mPrefs.getString(KEY_STUDENT, "");
    }

    @Override
    public void setStudentName(String name) {
        mPrefs.edit().putString(KEY_STUDENT, name).apply();
    }
}
