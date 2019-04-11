package net.winnerawan.androidx.ui.main.info;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import net.winnerawan.androidx.R;
import net.winnerawan.androidx.data.network.model.Info;
import net.winnerawan.androidx.di.component.ActivityComponent;
import net.winnerawan.androidx.ui.adapter.InfoAdapter;
import net.winnerawan.androidx.ui.base.BaseFragment;
import net.winnerawan.androidx.ui.main.chart.choropleth.ChoroplethChartActivity;
import net.winnerawan.androidx.ui.main.chart.circular.CircularChartActivity;

import javax.inject.Inject;
import java.util.List;

public class InfoFragment extends BaseFragment implements InfoView {

    @Inject
    InfoMvpPresenter<InfoView> presenter;

    @Inject
    InfoAdapter adapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.recycler_info) RecyclerView mRecyclerView;

    public static InfoFragment newInstance() {
        
        Bundle args = new Bundle();
        
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }

        return view;
    }

    @OnClick(R.id.trackChart)
    void trackChart() {
        Intent intent = new Intent(getBaseActivity(), CircularChartActivity.class);
        intent.putExtra("title", "Pendaftar Per Jalur");
        intent.putExtra("url", "http://pmb.unipma.ac.id/grafik_sebaran");
        startActivity(intent);
    }

    @OnClick(R.id.distributionChart)
    void distributionChart() {
        Intent intent = new Intent(getBaseActivity(), ChoroplethChartActivity.class);
        intent.putExtra("title", "Sebaran Asal Sekolah Berdasarkan Kota/Kabupaten");
        intent.putExtra("url", "http://pmb.unipma.ac.id/grafik_sebaran");
        startActivity(intent);
    }

    @Override
    protected void setUp(View view) {
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        presenter.getInfos();
    }

    @Override
    public void showInfos(List<Info> infos) {
        adapter.addItems(infos);
    }

    private void openFragment(Fragment fragment) {
        final FragmentTransaction transaction = getBaseActivity().getSupportFragmentManager().beginTransaction();
//        transaction.setCustomAnimations(R.anim.anim_pop_left, R.anim.anim_push_left);
//        transaction.addToBackStack(null);
        transaction.replace(R.id.main_container, fragment).commit();
    }
}
