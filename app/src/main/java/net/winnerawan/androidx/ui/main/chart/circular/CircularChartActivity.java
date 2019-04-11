package net.winnerawan.androidx.ui.main.chart.circular;

import android.os.Bundle;
import net.winnerawan.androidx.ui.base.BaseActivity;

import javax.inject.Inject;

public class CircularChartActivity extends BaseActivity implements CircularChartView {

    @Inject
    CircularChartMvpPresenter<CircularChartView> presenter;


    @Override
    protected void setUp() {

    }
}
