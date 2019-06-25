package id.ac.unipma.pmb.ui.input.track;

import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.ui.base.BasePresenter;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class InputTrackPresenter<V extends InputTrackView> extends BasePresenter<V> implements InputTrackMvpPresenter<V> {

    @Inject
    public InputTrackPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
