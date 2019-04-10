package net.winnerawan.androidx.ui.main.sign;

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

public class SignFragment extends BaseFragment implements SignView {

    @Inject
    SignMvpPresenter<SignView> presenter;

    public static SignFragment newInstance() {

        Bundle args = new Bundle();

        SignFragment fragment = new SignFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign, container, false);

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
