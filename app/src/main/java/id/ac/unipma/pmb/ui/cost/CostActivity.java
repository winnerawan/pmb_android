package id.ac.unipma.pmb.ui.cost;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.levitnudi.legacytableview.LegacyTableView;
import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.data.network.model.Cost;
import id.ac.unipma.pmb.data.network.model.News;
import id.ac.unipma.pmb.ui.base.BaseActivity;
import id.ac.unipma.pmb.utils.ViewUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CostActivity extends BaseActivity implements CostView {

    @Inject
    CostMvpPresenter<CostView> presenter;

    @BindView(R.id.cost_table_view) LegacyTableView mTableView;
    @BindView(R.id.title_toolbar) TextView txtToolbar;
    @BindView(R.id.dim) FrameLayout DIM;
    @BindView(R.id.btnDialog) Button mBtnDialog;
    @BindView(R.id.dialog_cost) RelativeLayout mDialogCost;
    @BindView(R.id.card_cost_info) CardView mCard;
    private SlideUp slideUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost);
        getActivityComponent().inject(this);
        ViewUtils.setStatusBar(this);
        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();

        mBtnDialog.setOnClickListener(v -> {
            if (slideUp != null && slideUp.isVisible()) {
                slideUp.hide();
            }
        });


        mCard.setOnClickListener(v -> dialog().show());
    }

    @Override
    public void onBackPressed() {
        if (slideUp != null) {
            if (slideUp.isVisible()) {
                slideUp.hide();
            } else {
                finish();
            }
        } else {
            finish();
        }
    }

    @OnClick(R.id.back)
    void back() {
        finish();
    }


    @Override
    protected void setUp() {
        presenter.getCosts();
        txtToolbar.setText(getString(R.string.cost));
        LegacyTableView.insertLegacyTitle("PROGRAM STUDI", "BIAYA SEMESTER", "*PKKMB", "BPI GEL I",  "BPI GEL II",  "BPI GEL III");
        mTableView.setTitle(LegacyTableView.readLegacyTitle());
        mTableView.setContent(LegacyTableView.readLegacyContent());
        mTableView.build();
        mTableView.setZoomEnabled(true);
        mTableView.setShowZoomControls(true);

    }

    @Override
    public void showCosts(List<Cost> costs) {
        List<Cost> second = new ArrayList<Cost>(costs.subList(0, 24));
        initializeData(second);
    }

    private SlideUp dialog() {
        slideUp = new SlideUpBuilder(mDialogCost)
                .withListeners(new SlideUp.Listener.Events() {
                    @Override
                    public void onSlide(float percent) {
                        DIM.setAlpha(1 - (percent / 100));
                    }

                    @Override
                    public void onVisibilityChanged(int visibility) {
                        if (visibility== View.VISIBLE) {
                            mTableView.setVisibility(View.GONE);
                            mCard.setVisibility(View.INVISIBLE);
                        } else {
                            mTableView.setVisibility(View.VISIBLE);
                            mCard.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .withStartGravity(Gravity.BOTTOM)
                .withGesturesEnabled(true)
                .withStartState(SlideUp.State.SHOWED)
                .withSlideFromOtherView(mCard)
                .build();
        return slideUp;
    }

    private void initializeData(List<Cost> costs) {
        for (Cost cost: costs) {
            LegacyTableView.insertLegacyContent(
                    cost.getProgramStudy(),
                    cost.getBiayaSemester(),
                    cost.getPkkmb(),
                    cost.getPbiGel1(),
                    cost.getPbiGel2(),
                    cost.getPbiGel3()
            );
        }
    }
}
