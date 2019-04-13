package id.ac.unipma.pmb.ui.detail;

import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;import id.ac.unipma.pmb.ui.base.BasePresenter;
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
