package id.ac.unipma.pmb.ui.main.info;

import com.androidnetworking.error.ANError;
import io.reactivex.disposables.CompositeDisposable;
import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.ui.base.BasePresenter;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;
import javax.inject.Inject;

public class InfoPresenter<V extends InfoView> extends BasePresenter<V> implements InfoMvpPresenter<V> {

    @Inject
    public InfoPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getInfos() {
        getCompositeDisposable().add(getDataManager().getInfos()
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(infos -> {
                    if (!isViewAttached()) return;
                    if (infos==null) {
                        return;
                    }
                    getMvpView().showInfos(infos);
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
