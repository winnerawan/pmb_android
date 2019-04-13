package id.ac.unipma.pmb.ui.splash;

import javax.inject.Inject;

import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;import id.ac.unipma.pmb.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public class SplashPresenter<V extends SplashView> extends BasePresenter<V> implements SplashMvpPresenter<V> {

    @Inject
    public SplashPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
