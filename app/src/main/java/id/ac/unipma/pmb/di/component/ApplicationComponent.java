package id.ac.unipma.pmb.di.component;

import id.ac.unipma.pmb.PMB;
import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.di.module.ApplicationModule;
import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import id.ac.unipma.service.SyncService;
import dagger.Component;

import id.ac.unipma.pmb.di.ApplicationContext;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(PMB app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();}
