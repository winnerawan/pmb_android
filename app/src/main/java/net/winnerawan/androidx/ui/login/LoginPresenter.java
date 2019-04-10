package net.winnerawan.androidx.ui.login;

import io.reactivex.disposables.CompositeDisposable;
import net.winnerawan.androidx.R;
import net.winnerawan.androidx.data.DataManager;
import net.winnerawan.androidx.ui.base.BasePresenter;
import net.winnerawan.androidx.utils.rx.SchedulerProvider;

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
