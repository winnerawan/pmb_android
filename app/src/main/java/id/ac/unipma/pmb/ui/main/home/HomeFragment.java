package id.ac.unipma.pmb.ui.main.home;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.anychart.core.sankey.elements.Flow;
import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;
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
import id.ac.unipma.pmb.ui.cost.CostActivity;
import id.ac.unipma.pmb.ui.detail.DetailActivity;
import id.ac.unipma.pmb.ui.helper.AutoScrollViewPager;
import id.ac.unipma.pmb.ui.login.LoginActivity;
import id.ac.unipma.pmb.ui.main.accreditation.AccreditationActivity;
import id.ac.unipma.pmb.ui.main.detailinfo.DetailInfoActivity;
import id.ac.unipma.pmb.ui.main.flow.FlowActivity;
import id.ac.unipma.pmb.ui.prody.ProdyActivity;
import id.ac.unipma.pmb.ui.search.SearchActivity;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends BaseFragment implements HomeView, AnnouncementSliderAdapter.Callback, MenuInfoAdapter.Callback, NewsAdapter.Callback {

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

    @BindView(R.id.login) TextView mLogin;

    private OnRequirementSelected mCallback;
    @BindView(R.id.shimmer0) ShimmerFrameLayout mShimmer0;
    @BindView(R.id.shimmer1) ShimmerFrameLayout mShimmer1;
    @BindView(R.id.shimmer2) ShimmerFrameLayout mShimmer2;

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

        if (presenter.getLoggedIn()) {
            mLogin.setVisibility(View.INVISIBLE);
        } else {
            mLogin.setVisibility(View.VISIBLE);
            mLogin.setOnClickListener(v -> {
                startActivity(new Intent(getBaseActivity(), LoginActivity.class));
//                getBaseActivity().finish();
            });
        }

        return view;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnRequirementSelected) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @OnClick(R.id.search)
    void search() {
        startActivity(new Intent(getBaseActivity(), SearchActivity.class));
    }

    @OnClick(R.id.requirements)
    void req() {
        mCallback.onRequirementSelected();
    }

    @OnClick(R.id.flow)
    void flow() {
        startActivity(new Intent(getBaseActivity(), FlowActivity.class));
    }

    @OnClick(R.id.cost)
    void cost() {
        startActivity(new Intent(getBaseActivity(), CostActivity.class));
    }
    
    @Override
    protected void setUp(View view) {
        mNewsAdapter.setCallback(this);
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
        List<News> second = new ArrayList<News>(news.subList(0, 7));
        mNewsAdapter.addItems(second);
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
        switch (info.getId()) {
            case 1:
                startActivity(new Intent(getBaseActivity(), ProdyActivity.class));
                break;
            case 2:
                startActivity(new Intent(getBaseActivity(), AccreditationActivity.class));
                break;
            case 3:
                Intent intent = new Intent(getBaseActivity(), DetailInfoActivity.class);
                Announcement announcement = new Announcement();
                announcement.setGambar("http://pics.unipma.ac.id/content/kalender/KalenderAkademik.pdf");
                intent.putExtra("announcement", announcement);
                startActivity(intent);
                break;
            case 4:
                startActivity(new Intent(getBaseActivity(), PlayerActivity.class));
                break;
        }
    }

    @Override
    public void onNewsSelected(News news) {
        Intent intent = new Intent(getBaseActivity(), DetailActivity.class);
        intent.putExtra("news", news);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mShimmer1!=null && mShimmer2!=null && mShimmer0!=null) {
            mShimmer1.startShimmer();
            mShimmer2.startShimmer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mShimmer1!=null && mShimmer2!=null && mShimmer0!=null) {
            mShimmer1.startShimmer();
            mShimmer2.startShimmer();
        }
    }

    @Override
    public void stopShimmer2() {
        if (mShimmer2!=null) {
            mShimmer2.stopShimmer();
            mShimmer2.setVisibility(View.GONE);
        }
    }

    @Override
    public void stopShimmer1() {
        if (mShimmer1!=null) {
            mShimmer1.stopShimmer();
            mShimmer1.setVisibility(View.GONE);
        }
    }

    @Override
    public void stopShimmer0() {
        if (mShimmer0!=null) {
            mShimmer0.stopShimmer();
            mShimmer0.setVisibility(View.GONE);
        }
    }

    private int dpToPx() {
        DisplayMetrics displayMetrics = Objects.requireNonNull(getActivity()).getResources().getDisplayMetrics();
        return Math.round(10 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public interface OnRequirementSelected {
        public void onRequirementSelected();
    }

}
