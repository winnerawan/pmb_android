package net.winnerawan.androidx.ui.main;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import net.winnerawan.androidx.R;
import net.winnerawan.androidx.ui.base.BaseActivity;
import net.winnerawan.androidx.ui.help.AboutFragment;
import net.winnerawan.androidx.ui.helper.NonSwipeableViewPager;
import net.winnerawan.androidx.ui.main.home.HomeFragment;
import net.winnerawan.androidx.ui.main.info.InfoFragment;
import net.winnerawan.androidx.ui.main.sign.SignFragment;
import net.winnerawan.androidx.utils.ViewUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainMvpPresenter<MainView> presenter;

    @BindView(R.id.navigation) BottomNavigationViewEx mNavigation;
    @BindView(R.id.main_container) NonSwipeableViewPager mViewpager;

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
