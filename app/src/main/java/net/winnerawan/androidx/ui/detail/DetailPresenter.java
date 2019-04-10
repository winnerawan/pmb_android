package net.winnerawan.androidx.ui.detail;

import net.winnerawan.androidx.utils.rx.SchedulerProvider;
import net.winnerawan.androidx.data.DataManager;
import net.winnerawan.androidx.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class DetailPresenter<V extends DetailView> extends BasePresenter<V> implements DetailMvpPresenter<V> {

    @Inject
    public DetailPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public String getIntersId() {
        return null;
    }
}
