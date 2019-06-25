package id.ac.unipma.pmb.ui.input.program;

import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.ui.base.BasePresenter;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class InputProgramPresenter<V extends InputProgramView> extends BasePresenter<V> implements InputProgramMvpPresenter<V> {

    @Inject
    public InputProgramPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
