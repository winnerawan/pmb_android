package net.winnerawan.androidx.ui.main.chart.choropleth;

import io.reactivex.disposables.CompositeDisposable;
import net.winnerawan.androidx.data.DataManager;
import net.winnerawan.androidx.ui.base.BasePresenter;
import net.winnerawan.androidx.utils.rx.SchedulerProvider;

import javax.inject.Inject;

public class ChoroplethChartPresenter<V extends ChoroplethChartView> extends BasePresenter<V> implements ChoroplethChartMvpPresenter<V> {

    @Inject
    public ChoroplethChartPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
