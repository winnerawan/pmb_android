package id.ac.unipma.pmb.ui.input.program;

import android.os.Bundle;
import butterknife.ButterKnife;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.ui.base.BaseActivity;
import id.ac.unipma.pmb.utils.ViewUtils;

import javax.inject.Inject;

public class InputProgramActivity extends BaseActivity implements InputProgramView {

    @Inject
    InputProgramMvpPresenter<InputProgramView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_program);
        getActivityComponent().inject(this);
        ViewUtils.setStatusBar(this);
        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();
    }


    @Override
    protected void setUp() {

    }
}
