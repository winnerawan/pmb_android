package id.ac.unipma.pmb.ui.cost;

import com.androidnetworking.error.ANError;
import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.ui.base.BasePresenter;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class CostPresenter<V extends CostView> extends BasePresenter<V> implements CostMvpPresenter<V> {

    @Inject
    public CostPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getCosts() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager().getCosts()
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(costs -> {
                    if (!isViewAttached()) return;
                    if (costs==null) {
                        return;
                    }
                    getMvpView().hideLoading();
                    getMvpView().showCosts(costs);
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
