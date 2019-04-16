package id.ac.unipma.pmb.ui.main.home;

import com.androidnetworking.error.ANError;
import io.reactivex.disposables.CompositeDisposable;
import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.ui.base.BasePresenter;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;
import javax.inject.Inject;

public class HomePresenter<V extends HomeView> extends BasePresenter<V> implements HomeMvpPresenter<V> {

    @Inject
    public HomePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getAnnouncements() {
        getCompositeDisposable().add(getDataManager().getAnnouncements()
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(announcements -> {
                    if (!isViewAttached()) return;
                    if (announcements==null) {
                        return;
                    }
                    getMvpView().showAnnouncements(announcements);
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
    public void getNews() {
        getCompositeDisposable().add(getDataManager().getNews()
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(news -> {
                    if (!isViewAttached()) return;
                    if (news==null) {
                        return;
                    }
                    getMvpView().showNews(news);
//                    getMvpView().stopShimmer();
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
    public void getMenuInfos() {
        getCompositeDisposable().add(getDataManager().getMenuInfo()
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(menuInfos -> {
                    if (!isViewAttached()) return;
                    if (menuInfos==null) {
                        return;
                    }
                    getMvpView().showMenuInfos(menuInfos);
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
    public boolean getLoggedIn() {
        return getDataManager().isLoggedIn();
    }
}
