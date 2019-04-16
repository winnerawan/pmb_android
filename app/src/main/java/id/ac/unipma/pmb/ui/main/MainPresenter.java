package id.ac.unipma.pmb.ui.main;

import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;import id.ac.unipma.pmb.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class MainPresenter<V extends MainView> extends BasePresenter<V> implements MainMvpPresenter<V>{

    @Inject
    public MainPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public boolean isLoggedIn() {
        return getDataManager().isLoggedIn();
    }
}
