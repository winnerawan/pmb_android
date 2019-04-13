package id.ac.unipma.pmb.ui.search;

import id.ac.unipma.pmb.utils.KeyboardUtils;
import id.ac.unipma.pmb.utils.ViewUtils;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.data.network.model.Selection;
import id.ac.unipma.pmb.ui.adapter.SearchAdapter;
import id.ac.unipma.pmb.ui.base.BaseActivity;

import javax.inject.Inject;
import java.util.List;

public class SearchActivity extends BaseActivity implements SearchView, SearchAdapter.Callback {

    @Inject
    SearchMvpPresenter<SearchView> presenter;

    @Inject
    SearchAdapter adapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.recycler_results) RecyclerView mRecyclerView;

    @BindView(R.id.input_search) EditText txtSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();
        ViewUtils.setStatusBar(this);

        //search action
        txtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (adapter.getItemCount()>0) {
                    adapter.clear();
                }
                presenter.search(txtSearch.getText().toString());
                return true;
            }
            return false;
        });
    }

    @OnClick(R.id.img_clear_search)
    void clear() {
        txtSearch.setText("");
        adapter.clear();
        KeyboardUtils.showSoftInput(txtSearch, this);
    }

    @OnClick(R.id.img_back)
    void back() {
        finish();
    }


    @Override
    protected void setUp() {
        adapter.setCallback(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showSelections(List<Selection> selections) {
        adapter.addItems(selections);
    }

    @Override
    public void onEmptyViewRetryClick() {
        txtSearch.setText("");
        adapter.clear();
        KeyboardUtils.showSoftInput(txtSearch, this);
    }
}