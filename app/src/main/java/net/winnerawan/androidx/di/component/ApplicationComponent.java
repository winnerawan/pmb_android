package net.winnerawan.androidx.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import net.winnerawan.androidx.AndroidX;
import net.winnerawan.androidx.data.DataManager;
import net.winnerawan.androidx.di.module.ApplicationModule;
import net.winnerawan.androidx.service.SyncService;
import dagger.Component;

import net.winnerawan.androidx.di.ApplicationContext;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(AndroidX app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();}
