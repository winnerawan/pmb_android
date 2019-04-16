package id.ac.unipma.pmb.ui.main.sign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.di.component.ActivityComponent;
import id.ac.unipma.pmb.ui.account.AccountFragment;
import id.ac.unipma.pmb.ui.base.BaseFragment;

import javax.inject.Inject;

public class SignFragment extends BaseFragment implements SignView {

    @Inject
    SignMvpPresenter<SignView> presenter;

    @BindView(R.id.username) AppCompatEditText txtUsername;
    @BindView(R.id.password) AppCompatEditText txtPassword;

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

    @OnClick(R.id.btnLogin)
    void login() {
        presenter.login(txtUsername.getText().toString(), txtPassword.getText().toString());
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void replaceSignFragment() {
        openFragment();
    }

    private void openFragment() {
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_sign, AccountFragment.newInstance()).commit();
    }
}
