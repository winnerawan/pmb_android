package id.ac.unipma.pmb.ui.main.info;

import android.content.Intent;
import android.os.Bundle;
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
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.data.network.model.Info;
import id.ac.unipma.pmb.di.component.ActivityComponent;
import id.ac.unipma.pmb.ui.adapter.InfoAdapter;
import id.ac.unipma.pmb.ui.base.BaseFragment;
import id.ac.unipma.pmb.ui.main.chart.ChartActivity;
import id.ac.unipma.pmb.ui.main.detailinfo.DetailInfoActivity;

import javax.inject.Inject;
import java.util.List;

public class InfoFragment extends BaseFragment implements InfoView, InfoAdapter.Callback {

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


    @OnClick(R.id.distributionChart)
    void distributionChart() {
        Intent intent = new Intent(getBaseActivity(), ChartActivity.class);
        intent.putExtra("title", "Sebaran Asal Sekolah Berdasarkan Wilayah");
        intent.putExtra("url", "http://pmb.unipma.ac.id/grafik_sebaran");
        startActivity(intent);
    }

    @Override
    protected void setUp(View view) {
        adapter.setCallback(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        presenter.getInfos();
    }

    @Override
    public void showInfos(List<Info> infos) {
        adapter.addItems(infos);
    }

    @Override
    public void onInfoSelected(Info info) {
        Intent intent = new Intent(getBaseActivity(), DetailInfoActivity.class);
        intent.putExtra("info", info);
        startActivity(intent);
        //todo set different action
    }
}
