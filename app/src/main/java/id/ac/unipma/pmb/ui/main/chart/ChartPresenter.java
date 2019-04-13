package id.ac.unipma.pmb.ui.main.chart;

import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;import io.reactivex.disposables.CompositeDisposable;
import id.ac.unipma.pmb.ui.base.BasePresenter;

import javax.inject.Inject;

public class ChartPresenter<V extends ChartView> extends BasePresenter<V> implements ChartMvpPresenter<V> {

    @Inject
    public ChartPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
