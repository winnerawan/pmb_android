package id.ac.unipma.pmb.ui.prody;

import com.androidnetworking.error.ANError;
import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.ui.base.BasePresenter;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class ProdyPresenter<V extends ProdyView> extends BasePresenter<V> implements ProdyMvpPresenter<V> {

    @Inject
    public ProdyPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getProgramStudy() {
        getCompositeDisposable().add(getDataManager().getProgramStudy()
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(faculties -> {
                    if (!isViewAttached()) return;
                    if (faculties==null) {
                        return;
                    }
                    getMvpView().showProgramStudy(faculties);
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
