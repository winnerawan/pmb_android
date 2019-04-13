package id.ac.unipma.pmb.ui.main.sign;

import io.reactivex.disposables.CompositeDisposable;
import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.ui.base.BasePresenter;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;
import javax.inject.Inject;

public class SignPresenter<V extends SignView> extends BasePresenter<V> implements SignMvpPresenter<V> {

    @Inject
    public SignPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
