package net.winnerawan.androidx.ui.main.detailinfo;

import io.reactivex.disposables.CompositeDisposable;
import net.winnerawan.androidx.data.DataManager;
import net.winnerawan.androidx.ui.base.BasePresenter;
import net.winnerawan.androidx.utils.rx.SchedulerProvider;

import javax.inject.Inject;

public class DetailInfoPresenter<V extends DetailInfoView> extends BasePresenter<V> implements DetailInfoMvpPresenter<V> {

    @Inject
    public DetailInfoPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
