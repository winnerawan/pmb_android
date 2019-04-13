package id.ac.unipma.pmb.di.module;

import id.ac.unipma.pmb.data.AppDataManager;
import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.data.db.AppDbHelper;
import id.ac.unipma.pmb.data.db.DbHelper;
import id.ac.unipma.pmb.data.network.ApiHelper;
import id.ac.unipma.pmb.data.network.AppApiHelper;
import id.ac.unipma.pmb.data.prefs.AppPreferencesHelper;
import id.ac.unipma.pmb.data.prefs.PreferencesHelper;
import id.ac.unipma.pmb.utils.AppConstants;
import android.app.Application;
import android.content.Context;



import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.ac.unipma.pmb.di.ApplicationContext;
import id.ac.unipma.pmb.di.DatabaseInfo;
import id.ac.unipma.pmb.di.PreferenceInfo;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

//    @Provides
//    @Singleton
//    ApiHeader provideApiHeader(DbHelper header) {
//        return header;
//    }



}
