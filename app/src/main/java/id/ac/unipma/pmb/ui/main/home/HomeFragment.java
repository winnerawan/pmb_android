package id.ac.unipma.pmb.ui.main.home;

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
import id.ac.unipma.pmb.data.network.model.Prestation;
import id.ac.unipma.pmb.di.component.ActivityComponent;
import id.ac.unipma.pmb.ui.adapter.AnnouncementAdapter;
import id.ac.unipma.pmb.ui.adapter.PrestationAdapter;
import id.ac.unipma.pmb.ui.base.BaseFragment;
import id.ac.unipma.pmb.ui.helper.AutoScrollViewPager;
import id.ac.unipma.pmb.ui.search.SearchActivity;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends BaseFragment implements HomeView, AnnouncementAdapter.Callback {

    @Inject
    HomeMvpPresenter<HomeView> presenter;

    @Inject
    AnnouncementAdapter mAnnouncementAdapter;

    @Inject
    PrestationAdapter mPrestationAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.view_pager_slider) AutoScrollViewPager mSlidePager;

    @BindView(R.id.recycler_prestations) RecyclerView mRecylerView;

    @BindView(R.id.shimmer) ShimmerFrameLayout mShimmer;

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }

        return view;
    }

    @OnClick(R.id.searchbar)
    void search() {
        startActivity(new Intent(getBaseActivity(), SearchActivity.class));
    }
    
    @Override
    protected void setUp(View view) {
        mSlidePager.setPageMargin(dpToPx());
        mSlidePager.startAutoScroll();
        mSlidePager.setInterval(5000);
        mSlidePager.setCycle(true);
        mSlidePager.setStopScrollWhenTouch(true);
        mRecylerView.setLayoutManager(mLayoutManager);
        mRecylerView.setAdapter(mPrestationAdapter);
        presenter.getAnnouncements();
        presenter.getPrestations();
    }

    @Override
    public void showAnnouncements(List<Announcement> announcements) {
        AnnouncementSliderAdapter mSliderAdapter = new AnnouncementSliderAdapter(announcements, getBaseActivity());
        mSlidePager.setAdapter(mSliderAdapter);
    }

    @Override
    public void showPrestations(List<Prestation> prestations) {
        mPrestationAdapter.addItems(prestations);
    }

    @Override
    public void onAnnouncementSelected(Announcement announcement) {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mShimmer!=null) {
            mShimmer.startShimmer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mShimmer!=null) {
            mShimmer.startShimmer();
        }
    }

    @Override
    public void stopShimmer() {
        if (mShimmer!=null) {
            mShimmer.stopShimmer();
            mShimmer.setVisibility(View.GONE);
        }
    }

    private int dpToPx() {
        DisplayMetrics displayMetrics = Objects.requireNonNull(getActivity()).getResources().getDisplayMetrics();
        return Math.round(10 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
