package id.ac.unipma.pmb.data;

import id.ac.unipma.pmb.data.db.DbHelper;
import id.ac.unipma.pmb.data.network.ApiHelper;
import id.ac.unipma.pmb.data.network.model.*;
import id.ac.unipma.pmb.data.prefs.PreferencesHelper;
import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import id.ac.unipma.pmb.data.network.model.*;
import id.ac.unipma.pmb.di.ApplicationContext;

import java.io.File;
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
    public int getStep() {
        return mPreferencesHelper.getStep();
    }

    @Override
    public void setStep(int step) {
        mPreferencesHelper.setStep(step);
    }

    @Override
    public Single<List<Announcement>> getAnnouncements() {
        return mApiHelper.getAnnouncements();
    }

    @Override
    public Single<List<News>> getNews() {
        return mApiHelper.getNews();
    }

    @Override
    public Single<List<Selection>> search(String keyword) {
        return mApiHelper.search(keyword);
    }

    @Override
    public Single<List<Info>> getInfos() {
        return mApiHelper.getInfos();
    }

    @Override
    public Single<List<MenuInfo>> getMenuInfo() {
        return mApiHelper.getMenuInfo();
    }

    @Override
    public Single<ContentInfo> getCostInfo(String link) {
        return mApiHelper.getCostInfo(link);
    }

    @Override
    public Single<ContentInfo> getTrackInfo(String link) {
        return mApiHelper.getTrackInfo(link);
    }

    @Override
    public Single<ContentInfo> getScheduleInfo(String link) {
        return mApiHelper.getScheduleInfo(link);
    }

    @Override
    public Single<ContentInfo> getStudyInfo(String link) {
        return mApiHelper.getStudyInfo(link);
    }

    @Override
    public Single<File> getKwitansi(String noReg) {
        return mApiHelper.getKwitansi(noReg);
    }

    @Override
    public Single<LoginResponse> login(String username, String password) {
        return mApiHelper.login(username, password);
    }


}

