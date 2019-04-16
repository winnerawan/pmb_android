package id.ac.unipma.pmb.ui.account;

import io.reactivex.disposables.CompositeDisposable;
import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.ui.base.BasePresenter;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;
import javax.inject.Inject;

public class AccountPresenter<V extends AccountView> extends BasePresenter<V> implements AccountMvpPresenter<V> {

    @Inject
    public AccountPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public String getStudentName() {
        return getDataManager().getStudentName();
    }

    @Override
    public int getStep() {
        return getDataManager().getStep();
    }

    @Override
    public void logout() {
        getDataManager().setLoggedIn(false);
        getMvpView().reopen();
    }
}
