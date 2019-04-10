package net.winnerawan.androidx.ui.main.info;

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
import net.winnerawan.androidx.R;
import net.winnerawan.androidx.data.network.model.Info;
import net.winnerawan.androidx.data.network.model.Prestation;
import net.winnerawan.androidx.di.component.ActivityComponent;
import net.winnerawan.androidx.ui.adapter.InfoAdapter;
import net.winnerawan.androidx.ui.base.BaseFragment;

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
}
