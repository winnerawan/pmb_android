package net.winnerawan.androidx.ui.detail;

import android.os.Bundle;
import butterknife.ButterKnife;
import net.winnerawan.androidx.R;
import net.winnerawan.androidx.ui.base.BaseActivity;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity implements DetailView {

    @Inject
    DetailMvpPresenter<DetailView> presenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();
    }



    @Override
    protected void setUp() {



//        mInterstitialAd = new InterstitialAd(getApplicationContext());
//        mInterstitialAd.setAdUnitId(presenter.getIntersId());
//
//        mInterstitialAd.loadAd(adRequest);


    }


}
