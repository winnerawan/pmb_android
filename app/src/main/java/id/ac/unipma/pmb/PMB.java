package id.ac.unipma.pmb;

import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.di.component.ApplicationComponent;
import id.ac.unipma.pmb.di.component.DaggerApplicationComponent;
import id.ac.unipma.pmb.di.module.ApplicationModule;
import id.ac.unipma.pmb.utils.AppLogger;
import android.app.Application;

import androidx.multidex.MultiDex;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


import javax.inject.Inject;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public class PMB extends Application {

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
