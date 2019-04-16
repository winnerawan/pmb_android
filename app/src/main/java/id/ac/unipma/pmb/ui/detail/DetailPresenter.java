package id.ac.unipma.pmb.ui.detail;

import com.androidnetworking.error.ANError;
import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;import id.ac.unipma.pmb.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class DetailPresenter<V extends DetailView> extends BasePresenter<V> implements DetailMvpPresenter<V> {

    @Inject
    public DetailPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getNewsDetail(String link) {
        getCompositeDisposable().add(getDataManager().getNewsDetail(link)
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(news -> {
                    if (!isViewAttached()) return;
                    if (news==null) {
                        return;
                    }
                    getMvpView().stopShimmer();
                    getMvpView().showNewsDetail(news);
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
