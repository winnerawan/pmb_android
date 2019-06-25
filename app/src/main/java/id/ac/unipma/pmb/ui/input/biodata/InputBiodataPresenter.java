package id.ac.unipma.pmb.ui.input.biodata;

import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.ui.base.BasePresenter;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class InputBiodataPresenter<V extends InputBiodataView> extends BasePresenter<V> implements InputBiodataMvpPresenter<V> {

    @Inject
    public InputBiodataPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
