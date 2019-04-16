package id.ac.unipma.pmb.ui.main.sign;

import com.androidnetworking.error.ANError;
import id.ac.unipma.pmb.R;
import id.ac.unipma.pmb.data.network.model.LoginResponse;
import io.reactivex.disposables.CompositeDisposable;
import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.ui.base.BasePresenter;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;
import io.reactivex.functions.Consumer;

import javax.inject.Inject;

public class SignPresenter<V extends SignView> extends BasePresenter<V> implements SignMvpPresenter<V> {

    @Inject
    public SignPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void login(String username, String password) {
        if (username.isEmpty()) {
            getMvpView().onError(R.string.message_empty_name);
            return;
        }
        if (password.isEmpty()) {
            getMvpView().onError(R.string.message_empty_password);
            return;
        }
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager().login(username, password)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(loginResponse -> {
                    getMvpView().hideLoading();
                    if (!isViewAttached()) return;
                    if (loginResponse==null) {
                        return;
                    }
                    if (!loginResponse.getError()) {
                        getMvpView().replaceSignFragment();
                    } else {
                        getMvpView().onError(R.string.wrong_auth);
                    }
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();

                    if (throwable instanceof ANError) {
                        ANError anError = (ANError) throwable;
                        handleApiError(anError);
                    }
                }));
    }
}
