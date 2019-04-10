package net.winnerawan.androidx.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import net.winnerawan.androidx.data.db.DbHelper;
import net.winnerawan.androidx.data.network.ApiHelper;
import net.winnerawan.androidx.data.network.model.Announcement;
import net.winnerawan.androidx.data.network.model.Info;
import net.winnerawan.androidx.data.network.model.Prestation;
import net.winnerawan.androidx.data.network.model.Selection;
import net.winnerawan.androidx.data.prefs.PreferencesHelper;
import net.winnerawan.androidx.di.ApplicationContext;

import java.util.List;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

//    @Override
//    public ApiHeader getApiHeader() {
//        return mApiHelper.getApiHeader();
//    }


    @Override
    public boolean isLoggedIn() {
        return mPreferencesHelper.isLoggedIn();
    }

    @Override
    public void setLoggedIn(boolean loggedIn) {
        mPreferencesHelper.setLoggedIn(loggedIn);
    }


    @Override
    public boolean isFirstTime() {
        return mPreferencesHelper.isFirstTime();
    }

    @Override
    public void setFirstTime(boolean isFirstTime) {
        mPreferencesHelper.setFirstTime(isFirstTime);
    }

    @Override
    public Single<List<Announcement>> getAnnouncements() {
        return mApiHelper.getAnnouncements();
    }

    @Override
    public Single<List<Prestation>> getPrestations() {
        return mApiHelper.getPrestations();
    }

    @Override
    public Single<List<Selection>> search(String keyword) {
        return mApiHelper.search(keyword);
    }

    @Override
    public Single<List<Info>> getInfos() {
        return mApiHelper.getInfos();
    }
}

