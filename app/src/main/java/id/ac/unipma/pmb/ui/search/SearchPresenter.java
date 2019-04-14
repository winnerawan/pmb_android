package id.ac.unipma.pmb.ui.search;


import id.ac.unipma.pmb.utils.rx.SchedulerProvider;import com.androidnetworking.error.ANError;
import io.reactivex.disposables.CompositeDisposable;
import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.ui.base.BasePresenter;

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

    @Override
    public void getKwitansi(String noReg) {
        getCompositeDisposable().add(getDataManager().getKwitansi(noReg)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(file -> {
                    if (!isViewAttached()) return;
                    if (file==null) {
                        return;
                    }
                    getMvpView().showKwitansi(file);
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
