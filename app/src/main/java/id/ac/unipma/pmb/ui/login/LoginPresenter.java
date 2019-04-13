package id.ac.unipma.pmb.ui.login;

import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.ui.base.BasePresenter;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class LoginPresenter<V extends LoginView> extends BasePresenter<V> implements LoginMvpPresenter<V>{

    @Inject
    public LoginPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void login(String username, String password) {
//        if (username.isEmpty()) {
//            getMvpView().onError(R.string.message_empty_name);
//            return;
//        }
//        if (password.isEmpty()) {
//            getMvpView().onError(R.string.message_empty_password);
//            return;
//        }

        //todo
        getMvpView().gotoMainActivity();
    }
}
