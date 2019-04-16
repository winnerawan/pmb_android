package id.ac.unipma.pmb.ui.main;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;
import id.ac.unipma.pmb.ui.account.AccountFragment;
import id.ac.unipma.pmb.ui.base.BaseActivity;
import id.ac.unipma.pmb.ui.help.AboutFragment;
import id.ac.unipma.pmb.ui.helper.NonSwipeableViewPager;
import id.ac.unipma.pmb.ui.main.home.HomeFragment;
import id.ac.unipma.pmb.ui.main.info.InfoFragment;
import id.ac.unipma.pmb.ui.main.sign.SignFragment;
import id.ac.unipma.pmb.utils.ViewUtils;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import id.ac.unipma.pmb.R;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainView, HomeFragment.OnRequirementSelected {

    @Inject
    MainMvpPresenter<MainView> presenter;

    @BindView(R.id.navigation)
    BottomNavigationViewEx mNavigation;
    @BindView(R.id.main_container)
    NonSwipeableViewPager mViewpager;
    @BindView(R.id.dim) FrameLayout DIM;
    @BindView(R.id.dialog_req) RelativeLayout mDialogReq;
    @BindView(R.id.btnDialog) Button mBtnDialog;
    private SlideUp slideUp;
    private boolean isLoggedIn;
    private Callback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();

        mBtnDialog.setOnClickListener(v -> {
            if (slideUp != null && slideUp.isVisible()) {
                slideUp.hide();
            }
        });
    }


    @Override
    protected void setUp() {
        isLoggedIn = presenter.isLoggedIn();
        ViewUtils.setStatusBar(this);
        initTabs();
    }

    private void initTabs() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ride_rewrite_neo_sans_medium.ttf");
        mNavigation.setTypeface(typeface);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(InfoFragment.newInstance());
        if (presenter.isLoggedIn()) {
            fragments.add(AccountFragment.newInstance());
            mNavigation.inflateMenu(R.menu.bottom_navigation);
        } else {
            mNavigation.inflateMenu(R.menu.bottom_navigation_not_sign);
        }
        fragments.add(AboutFragment.newInstance());
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);

        mViewpager.setAdapter(adapter);
        mNavigation.setupWithViewPager(mViewpager);
    }

    @Override
    public void onRequirementSelected() {
        dialogReq().show();
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

    public interface Callback {
        void onReqSelected();
    }

    public void setCallback(Callback call) {
        this.callback = call;
    }

    private SlideUp dialogReq() {
        slideUp = new SlideUpBuilder(mDialogReq)
                .withListeners(new SlideUp.Listener.Events() {
                    @Override
                    public void onSlide(float percent) {
                        DIM.setAlpha(1 - (percent / 100));

                    }

                    @Override
                    public void onVisibilityChanged(int visibility) {
                        if (visibility== View.VISIBLE) {
                            mNavigation.setVisibility(View.INVISIBLE);
                        } else {
                            mNavigation.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .withStartGravity(Gravity.BOTTOM)
//                .withLoggingEnabled(true)
//                .withAutoSlideDuration(2000)
                .withGesturesEnabled(true)
                .withStartState(SlideUp.State.SHOWED)
//                .withSlideFromOtherView(findViewById(R.id.rootview))
                .build();
        return slideUp;
    }
}
