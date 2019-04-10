package net.winnerawan.androidx.ui.help;

import io.reactivex.disposables.CompositeDisposable;
import net.winnerawan.androidx.data.DataManager;
import net.winnerawan.androidx.ui.base.BasePresenter;
import net.winnerawan.androidx.utils.rx.SchedulerProvider;

import javax.inject.Inject;

public class AboutPresenter<V extends AboutView> extends BasePresenter<V> implements AboutMvpPresenter<V> {

    @Inject
    public AboutPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
