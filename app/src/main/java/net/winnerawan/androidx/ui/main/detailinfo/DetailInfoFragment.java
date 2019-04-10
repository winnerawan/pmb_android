package net.winnerawan.androidx.ui.main.detailinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import net.winnerawan.androidx.R;
import net.winnerawan.androidx.di.component.ActivityComponent;
import net.winnerawan.androidx.ui.base.BaseFragment;

import javax.inject.Inject;

public class DetailInfoFragment extends BaseFragment implements DetailInfoView {

    @Inject
    DetailInfoMvpPresenter<DetailInfoView> presenter;

    public static DetailInfoFragment newInstance() {

        Bundle args = new Bundle();

        DetailInfoFragment fragment = new DetailInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailinfo, container, false);

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

    }
}
