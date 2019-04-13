package id.ac.unipma.pmb.ui.main.chart;

import id.ac.unipma.pmb.utils.AppLogger;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Map;
import com.anychart.charts.Pie;
import com.anychart.core.map.series.Choropleth;
import com.anychart.core.ui.ColorRange;
import com.anychart.enums.*;
import com.anychart.scales.LinearColor;
import com.wang.avi.AVLoadingIndicatorView;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.ui.base.BaseActivity;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends BaseActivity implements ChartView {

    @Inject
    ChartMvpPresenter<ChartView> presenter;

    @BindView(R.id.title) TextView txtTitle;

    @BindView(R.id.rootLayout) RelativeLayout mRootLayout;

    @BindView(R.id.progress_bar) AVLoadingIndicatorView mProgress;

    @BindView(R.id.title_toolbar) TextView txtToolbar;


    private AnyChartView mChoroplethChart, mCircularChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        getActivityComponent().inject(this);
        overridePendingTransition(R.anim.anim_pop_left, R.anim.anim_push_left);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();
    }


    @Override
    protected void setUp() {
        txtToolbar.setText(getString(R.string.chart));
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            setupMap(bundle.getString("title"), bundle.getString("url"));
            setupCircular();
        }
    }

    @OnClick(R.id.back)
    void back() {
        finishAction();
    }

    private void setupMap(String title, String url) {
        mChoroplethChart = findViewById(R.id.choropleth_chart);
        APIlib.getInstance().setActiveAnyChartView(mChoroplethChart);
        mChoroplethChart.setProgressBar(mProgress);
        Map map = AnyChart.map();
        txtTitle.setText(title);
        map.credits()
                .enabled(true)
                .url(url)
                .text(title)
                .logoSrc("http://pmb.unipma.ac.id/favicon.ico");
        map.geoData("anychart.maps.indonesia");
        ColorRange colorRange = map.colorRange();
        colorRange.enabled(true)
                .colorLineSize(10)
                .stroke("#B9B9B9")
                .labels("{ 'padding': 3 }")
                .labels("{ 'size': 7 }");
        colorRange.ticks()
                .enabled(true)
                .stroke("#B9B9B9")
                .position(SidePosition.OUTSIDE)
                .length(10);
        colorRange.minorTicks()
                .enabled(true)
                .stroke("#B9B9B9")
                .position("outside")
                .length(5);
        map.interactivity().selectionMode(SelectionMode.NONE);
        map.padding(0, 0, 0, 0);
        map.background().fill("#9DD7FC");

//        map.zoom(2);

        Choropleth series = map.choropleth(getData());
        LinearColor linearColor = LinearColor.instantiate();
//        linearColor.colors(new String[]{ "#c2e9fb", "#81d4fa", "#01579b", "#002746"});
        linearColor.colors(new String[]{ "#A1D99B", "#74C476", "#41AB5D", "#006D2C"});
        series.colorScale(linearColor);
        series.hovered()
//                .fill("#f48fb1")
                .fill("#1273EB")
//                .stroke("#f99fb9");
                .stroke("#1963EB");
        series.selected()
                .fill("#c2185b")
                .stroke("#c2185b");
        series.labels().enabled(true);
        series.labels().fontSize(10);
        series.labels().fontColor("#212121");
        series.labels().format("{%Value}");

        series.tooltip()
                .useHtml(true)
                .format("function() {\n" +
                        "            return '<span style=\"font-size: 13px\">' + this.value + ' Mahasiswa</span>';\n" +
                        "          }");


        mChoroplethChart.addScript("file:///android_asset/indonesia.js");
        mChoroplethChart.addScript("file:///android_asset/proj4.js");
        mChoroplethChart.setChart(map);
        mRootLayout.setBackgroundColor(getResources().getColor(R.color.light_blue));

    }

    private List<DataEntry> getData() {
        List<DataEntry> data = new ArrayList<>();

        data.add(new CustomDataEntry("ID.JR", "Jawa Barat", 2));
        data.add(new CustomDataEntry("ID.JT", "Jawa Tengah", 4));
        data.add(new CustomDataEntry("ID.JI", "Jawa Timur", 209));
        data.add(new CustomDataEntry("ID.KT", "Kalimantan Tengah", 1));
        data.add(new CustomDataEntry("ID.KI", "Kalimantan Timur", 1));
        data.add(new CustomDataEntry("ID.KR", "Kepulauan Riau", 1));
        data.add(new CustomDataEntry("ID.LA", "Lampung", 1));
        data.add(new CustomDataEntry("ID.NT", "Nusa Tenggara Timur", 4));
        data.add(new CustomDataEntry("ID.RI", "Riau", 1));


        return data;
    }

    class CustomDataEntry extends DataEntry {
        public CustomDataEntry(String id, String name, Number value) {
            setValue("id", id);
            setValue("name", name);
            setValue("value", value);
        }
        public CustomDataEntry(String id, String name, Number value, LabelDataEntry label) {
            setValue("id", id);
            setValue("name", name);
            setValue("value", value);
            setValue("label", label);
        }
    }

    class LabelDataEntry extends DataEntry {
        LabelDataEntry(Boolean enabled) {
            setValue("enabled", enabled);
        }
    }

    private void setupCircular() {
        mCircularChart = findViewById(R.id.circular_chart);
        APIlib.getInstance().setActiveAnyChartView(mCircularChart);
        mCircularChart.setProgressBar(mProgress);

        Pie pie = AnyChart.pie();
        pie.background().fill("#9DD7FC");
        List<DataEntry> data = new ArrayList<>();
        data.add(new PieDataEntry("PMDK", 136, 230));
        data.add(new PieDataEntry("PNUAN", 69, 230));
        data.add(new PieDataEntry("TES", 12, 230));
        data.add(new PieDataEntry("TRANSFER", 13, 230));

        int a = 10;
        AppLogger.e("data size= "+data.get(0).generateJs());
        pie.tooltip().useHtml(true)
                .format("function() {\n" +
                        "            return '<span style=\"font-size: 13px\">' + this.value + ' Mahasiswa<br/></span>' ;\n" +
                        "          }");

        pie.data(data);

        pie.labels().position("inside");

        pie.legend()
                .fontColor("#000000")
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);
        mCircularChart.setChart(pie);
    }

    class PieDataEntry extends DataEntry {
        public PieDataEntry(String x, Number value, Number total) {
            setValue("x", x);
            setValue("value", value);
            setValue("total", total);
        }

        public PieDataEntry(Number x, Number value, Number total) {
            setValue("x", x);
            setValue("value", value);
            setValue("total", total);
        }
    }

    private void finishAction() {
        finish();
        overridePendingTransition(R.anim.anim_pop_right, R.anim.anim_push_right);
    }

    public void onBackPressed() {
        finishAction();
    }
}
