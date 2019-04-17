package id.ac.unipma.pmb.ui.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;
import id.ac.unipma.pmb.ui.account.AccountFragment;
import id.ac.unipma.pmb.ui.adapter.LanguageAdapter;
import id.ac.unipma.pmb.ui.base.BaseActivity;
import id.ac.unipma.pmb.ui.about.AboutFragment;
import id.ac.unipma.pmb.ui.helper.NonSwipeableViewPager;
import id.ac.unipma.pmb.ui.main.home.HomeFragment;
import id.ac.unipma.pmb.ui.main.info.InfoFragment;
import id.ac.unipma.pmb.utils.ViewUtils;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import id.ac.unipma.pmb.R;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends BaseActivity implements MainView,
        HomeFragment.OnRequirementSelected,
        AccountFragment.OnPrivacyPolicySelected, AccountFragment.OnChangeLanguage,
        LanguageAdapter.LocaleListener {

    @Inject
    MainMvpPresenter<MainView> presenter;
    @Inject
    LinearLayoutManager mLayoutManager;
    @BindView(R.id.navigation) BottomNavigationViewEx mNavigation;
    @BindView(R.id.main_container) NonSwipeableViewPager mViewpager;
    @BindView(R.id.dim) FrameLayout DIM;
    @BindView(R.id.dialog_req) RelativeLayout mDialogReq;
    @BindView(R.id.dialog_privacy) RelativeLayout mDialogPrivacy;
    @BindView(R.id.dialog_language) RelativeLayout mDialogLang;
    @BindView(R.id.btnDialogReq) Button mBtnDialogReq;
    @BindView(R.id.btnDialogPrivacy) Button mBtnDialogPrivacy;
    @BindView(R.id.htmlviewprivacy) HtmlTextView mHtmlPrivacy;
    @BindView(R.id.recycler_language) RecyclerView mRecyclerLanguages;

    private LanguageAdapter mLanguageAdapter;

    private String[] languages;
    private SlideUp slideUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();

        mBtnDialogReq.setOnClickListener(v -> {
            if (slideUp != null && slideUp.isVisible()) {
                slideUp.hide();
            }
        });
        mBtnDialogPrivacy.setOnClickListener(v -> {
            if (slideUp != null && slideUp.isVisible()) {
                slideUp.hide();
            }
        });
    }


    @Override
    protected void setUp() {
        ViewUtils.setStatusBar(this);
        initTabs();
        setUpLang();
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
    public void onPrivacyPolicySelected() {
        dialogPrivacy().show();
    }

    @Override
    public void onChangeLanguage() {
        dialogLanguage().show();
    }

    @Override
    public void setLocale(String values) {
        Locale myLocale = new Locale(values);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
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

    private void setUpLang() {
        mRecyclerLanguages.setItemAnimator(new DefaultItemAnimator());
        mRecyclerLanguages.setLayoutManager(mLayoutManager);
        languages = getResources().getStringArray(R.array.languages);
        mLanguageAdapter = new LanguageAdapter(this, languages);
        mRecyclerLanguages.setAdapter(mLanguageAdapter);
        mLanguageAdapter.setLocaleListener(this);
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
                .withGesturesEnabled(true)
                .withStartState(SlideUp.State.SHOWED)
                .build();
        return slideUp;
    }

    private SlideUp dialogPrivacy() {
        mHtmlPrivacy.setHtml(R.raw.privacy, new HtmlHttpImageGetter(mHtmlPrivacy));
        slideUp = new SlideUpBuilder(mDialogPrivacy)
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
                .withGesturesEnabled(true)
                .withStartState(SlideUp.State.SHOWED)
                .build();
        return slideUp;
    }

    private SlideUp dialogLanguage() {
        slideUp = new SlideUpBuilder(mDialogLang)
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
                .withGesturesEnabled(true)
                .withStartState(SlideUp.State.SHOWED)
                .build();
        return slideUp;
    }
}
