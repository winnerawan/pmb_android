package id.ac.unipma.pmb.ui.main.accreditation;

import android.os.Bundle;
import butterknife.ButterKnife;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.ui.base.BaseActivity;

import javax.inject.Inject;

public class AccreditationActivity extends BaseActivity implements AccreditationView {

    @Inject
    AccreditationMvpPresenter<AccreditationView> presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accreditation);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();

    }

    @Override
    protected void setUp() {

    }
}
