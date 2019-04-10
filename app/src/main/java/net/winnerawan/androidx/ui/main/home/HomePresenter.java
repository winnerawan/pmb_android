package net.winnerawan.androidx.ui.main.home;

import com.androidnetworking.error.ANError;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import net.winnerawan.androidx.data.DataManager;
import net.winnerawan.androidx.data.network.model.Announcement;
import net.winnerawan.androidx.ui.base.BasePresenter;
import net.winnerawan.androidx.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import java.util.List;

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
    public void getPrestations() {
        getCompositeDisposable().add(getDataManager().getPrestations()
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(prestations -> {
                    if (!isViewAttached()) return;
                    if (prestations==null) {
                        return;
                    }
                    getMvpView().showPrestations(prestations);
                    getMvpView().stopShimmer();
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
