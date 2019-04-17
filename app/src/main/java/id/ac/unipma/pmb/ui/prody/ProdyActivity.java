package id.ac.unipma.pmb.ui.prody;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.data.network.model.Faculty;
import id.ac.unipma.pmb.ui.adapter.ProdyAdapter;
import id.ac.unipma.pmb.ui.base.BaseActivity;
import net.cachapa.expandablelayout.ExpandableLayout;

import javax.inject.Inject;
import java.util.List;

public class ProdyActivity extends BaseActivity implements ProdyView {

    @Inject
    ProdyMvpPresenter<ProdyView> presenter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.recycler_prody) RecyclerView mRecyclerView;

    private ProdyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prody);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {
        mRecyclerView.setLayoutManager(mLayoutManager);
        presenter.getProgramStudy();
    }

    @Override
    public void showProgramStudy(List<Faculty> faculties) {
        adapter = new ProdyAdapter(mRecyclerView, faculties);
//        adapter.addItems(faculties);
        mRecyclerView.setAdapter(adapter);
    }
}
