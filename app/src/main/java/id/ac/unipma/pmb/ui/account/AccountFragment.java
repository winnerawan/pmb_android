package id.ac.unipma.pmb.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.di.component.ActivityComponent;
import id.ac.unipma.pmb.ui.base.BaseFragment;
import id.ac.unipma.pmb.ui.main.MainActivity;
import id.ac.unipma.pmb.utils.AppUtils;

import javax.inject.Inject;

public class AccountFragment extends BaseFragment implements AccountView {

    @Inject
    AccountMvpPresenter<AccountView> presenter;

    @BindView(R.id.userName) TextView mUsername;

    @BindView(R.id.title_toolbar) TextView mToolbar;

    @BindView(R.id.container_step1) LinearLayout mStep1;

    @BindView(R.id.container_step2) LinearLayout mStep2;

    @BindView(R.id.container_step3) LinearLayout mStep3;

    @BindView(R.id.container_step4) LinearLayout mStep4;

    public static AccountFragment newInstance() {

        Bundle args = new Bundle();

        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }

        return view;
    }

    @OnClick(R.id.container_rate_app)
    void rate() {
        AppUtils.openPlayStoreForApp(getBaseActivity());
    }

    @OnClick(R.id.logOut)
    void logout() {
        presenter.logout();
    }

    @Override
    protected void setUp(View view) {
        mToolbar.setText(getString(R.string.action_account));
        setView(presenter.getStep());
    }

    @Override
    public void reopen() {
        getBaseActivity().finish();
        startActivity(new Intent(getBaseActivity(), MainActivity.class));
    }

    private void setView(int step) {
        mUsername.setText(presenter.getStudentName());
        switch (step) {
            case 1:
                break;
            case 2:
                mStep1.setEnabled(false);
                break;
            case 3:
                mStep1.setEnabled(false);
                break;
            case 4:
                mStep1.setEnabled(false);
                mStep2.setEnabled(false);
                mStep3.setEnabled(false);
                mStep4.setEnabled(false);
                break;
            case 5:
                mStep1.setEnabled(false);
                mStep2.setEnabled(false);
                mStep3.setEnabled(false);
                mStep4.setEnabled(false);
                break;
        }
    }
}
