package net.winnerawan.androidx.ui.main.chart.circular;

import io.reactivex.disposables.CompositeDisposable;
import net.winnerawan.androidx.data.DataManager;
import net.winnerawan.androidx.ui.base.BasePresenter;
import net.winnerawan.androidx.utils.rx.SchedulerProvider;

import javax.inject.Inject;

public class CircularChartPresenter<V extends CircularChartView> extends BasePresenter<V> implements CircularChartMvpPresenter<V> {

    @Inject
    public CircularChartPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
