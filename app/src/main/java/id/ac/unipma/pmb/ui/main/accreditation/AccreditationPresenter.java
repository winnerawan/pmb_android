package id.ac.unipma.pmb.ui.main.accreditation;

import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.ui.base.BasePresenter;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class AccreditationPresenter<V extends AccreditationView> extends BasePresenter<V> implements AccreditationMvpPresenter<V> {

    @Inject
    public AccreditationPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
