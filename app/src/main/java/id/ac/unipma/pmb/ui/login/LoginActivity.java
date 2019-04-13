package id.ac.unipma.pmb.ui.login;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.ui.base.BaseActivity;
import id.ac.unipma.pmb.ui.main.MainActivity;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements LoginView {

    @Inject
    LoginMvpPresenter<LoginView> presenter;

    @BindView(R.id.username) AppCompatEditText txtUsername;
    @BindView(R.id.password) AppCompatEditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();
    }

    @OnClick(R.id.btnLogin)
    void onLogin() {
        presenter.login(txtUsername.getText().toString(), txtPassword.getText().toString());
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void gotoMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
