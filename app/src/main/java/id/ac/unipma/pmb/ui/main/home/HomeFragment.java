package id.ac.unipma.pmb.ui.main.home;

import android.text.TextUtils;
import android.widget.TextView;
import id.ac.unipma.pmb.data.network.model.Announcement;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.shimmer.ShimmerFrameLayout;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.data.network.model.MenuInfo;
import id.ac.unipma.pmb.data.network.model.News;
import id.ac.unipma.pmb.data.network.model.Prestation;
import id.ac.unipma.pmb.di.component.ActivityComponent;
import id.ac.unipma.pmb.ui.adapter.AnnouncementAdapter;
import id.ac.unipma.pmb.ui.adapter.MenuInfoAdapter;
import id.ac.unipma.pmb.ui.adapter.NewsAdapter;
import id.ac.unipma.pmb.ui.adapter.PrestationAdapter;
import id.ac.unipma.pmb.ui.base.BaseFragment;
import id.ac.unipma.pmb.ui.helper.AutoScrollViewPager;
import id.ac.unipma.pmb.ui.main.detailinfo.DetailInfoActivity;
import id.ac.unipma.pmb.ui.search.SearchActivity;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends BaseFragment implements HomeView, AnnouncementSliderAdapter.Callback, MenuInfoAdapter.Callback {

    @Inject
    HomeMvpPresenter<HomeView> presenter;

    @Inject
    NewsAdapter mNewsAdapter;

    @Inject
    MenuInfoAdapter infoAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.view_pager_slider) AutoScrollViewPager mSlidePager;

    @BindView(R.id.recycler_news) RecyclerView mRecylerView;

    @BindView(R.id.recycler_info) RecyclerView mRecylerInfo;

    @BindView(R.id.marqueetext) TextView mMarqueeText;
//    @BindView(R.id.shimmer) ShimmerFrameLayout mShimmer;

    public static HomeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_new, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }

        return view;
    }

    @OnClick(R.id.search)
    void search() {
        startActivity(new Intent(getBaseActivity(), SearchActivity.class));
    }
    
    @Override
    protected void setUp(View view) {
        mMarqueeText.setSelected(true);
        mMarqueeText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        mMarqueeText.setSingleLine(true);
        mSlidePager.setPageMargin(dpToPx());
        mSlidePager.startAutoScroll();
        mSlidePager.setInterval(5000);
        mSlidePager.setCycle(true);
        mSlidePager.setStopScrollWhenTouch(true);
        infoAdapter.setCallback(this);
        mRecylerInfo.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRecylerInfo.setAdapter(infoAdapter);
        mRecylerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRecylerView.setAdapter(mNewsAdapter);

        presenter.getAnnouncements();
        presenter.getMenuInfos();
        presenter.getNews();
    }

    @Override
    public void showAnnouncements(List<Announcement> announcements) {
        AnnouncementSliderAdapter mSliderAdapter = new AnnouncementSliderAdapter(announcements, getBaseActivity());
        mSliderAdapter.setCallback(this);
        mSlidePager.setAdapter(mSliderAdapter);
    }

    @Override
    public void showNews(List<News> news) {
        mNewsAdapter.addItems(news);
    }

    @Override
    public void onAnnouncementSelected(Announcement announcement) {
        if (announcement!=null) {
            Intent intent = new Intent(getBaseActivity(), DetailInfoActivity.class);
            intent.putExtra("announcement", announcement);
            startActivity(intent);
        }
    }

    @Override
    public void showMenuInfos(List<MenuInfo> menuInfos) {
        infoAdapter.addItems(menuInfos);
    }

    @Override
    public void onMenuInfoSelected(MenuInfo info) {
        if (info.getId()==4) {
            startActivity(new Intent(getBaseActivity(), PlayerActivity.class));
        }
    }

    //    @Override
//    public void onStart() {
//        super.onStart();
//        if (mShimmer!=null) {
//            mShimmer.startShimmer();
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (mShimmer!=null) {
//            mShimmer.startShimmer();
//        }
//    }
//
//    @Override
//    public void stopShimmer() {
//        if (mShimmer!=null) {
//            mShimmer.stopShimmer();
//            mShimmer.setVisibility(View.GONE);
//        }
//    }

    private int dpToPx() {
        DisplayMetrics displayMetrics = Objects.requireNonNull(getActivity()).getResources().getDisplayMetrics();
        return Math.round(10 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
