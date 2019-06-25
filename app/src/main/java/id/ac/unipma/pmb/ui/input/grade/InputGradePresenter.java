package id.ac.unipma.pmb.ui.input.grade;

import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.ui.base.BasePresenter;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class InputGradePresenter<V extends InputGradeView> extends BasePresenter<V> implements InputGradeMvpPresenter<V>{

    @Inject
    public InputGradePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
