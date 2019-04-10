package net.winnerawan.androidx.ui.main.search;


import com.androidnetworking.error.ANError;
import io.reactivex.disposables.CompositeDisposable;
import net.winnerawan.androidx.data.DataManager;
import net.winnerawan.androidx.ui.base.BasePresenter;
import net.winnerawan.androidx.utils.rx.SchedulerProvider;

import javax.inject.Inject;

public class SearchPresenter<V extends SearchView> extends BasePresenter<V> implements SearchMvpPresenter<V> {

    @Inject
    public SearchPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void search(String keyword) {
        getMvpView().hideKeyboard();
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager().search(keyword)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(selections -> {
                    if (!isViewAttached()) return;
                    if (selections==null) {
                        return;
                    }
                    getMvpView().showSelections(selections);
                    getMvpView().hideLoading();
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
