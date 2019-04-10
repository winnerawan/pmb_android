package net.winnerawan.androidx.ui.main;

import net.winnerawan.androidx.data.DataManager;
import net.winnerawan.androidx.utils.rx.SchedulerProvider;
import net.winnerawan.androidx.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class MainPresenter<V extends MainView> extends BasePresenter<V> implements MainMvpPresenter<V>{

    @Inject
    public MainPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
