package id.ac.unipma.pmb.ui.main;

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

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainMvpPresenter<MainView> presenter;

    @BindView(R.id.navigation) BottomNavigationViewEx mNavigation;
    @BindView(R.id.main_container)
    NonSwipeableViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();
    }


    @Override
    protected void setUp() {
        ViewUtils.setStatusBar(this);
        initTabs();
    }

    private void initTabs() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ride_rewrite_neo_sans_medium.ttf");
        mNavigation.setTypeface(typeface);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(InfoFragment.newInstance());
        fragments.add(SignFragment.newInstance());
        fragments.add(AboutFragment.newInstance());

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);

        mViewpager.setAdapter(adapter);
        mNavigation.setupWithViewPager(mViewpager);
    }
}
