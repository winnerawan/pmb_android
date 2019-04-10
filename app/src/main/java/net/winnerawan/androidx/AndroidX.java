package net.winnerawan.androidx;

import android.app.Application;

import androidx.multidex.MultiDex;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import com.crashlytics.android.Crashlytics;
import net.winnerawan.androidx.utils.AppLogger;
import net.winnerawan.androidx.data.DataManager;
import net.winnerawan.androidx.di.component.ApplicationComponent;
import net.winnerawan.androidx.di.component.DaggerApplicationComponent;
import net.winnerawan.androidx.di.module.ApplicationModule;
import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


import javax.inject.Inject;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public class AndroidX extends Application {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        MultiDex.install(this);
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/NeoSansPro_Medium.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build());
        mApplicationComponent.inject(this);
        AppLogger.init();
        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        System.gc();
    }

}
